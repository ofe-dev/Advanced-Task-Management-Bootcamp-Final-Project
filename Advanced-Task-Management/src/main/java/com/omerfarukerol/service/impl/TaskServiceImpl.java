package com.omerfarukerol.service.impl;

import com.omerfarukerol.entities.*;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.enums.TaskState;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.*;
import com.omerfarukerol.repository.*;
import com.omerfarukerol.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public CreateTaskResponseModel createTask(CreateTaskRequestModel requestModel) {
        TeamMember teamMember = teamMemberRepository.findById(requestModel.getTeamMemberId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Team Member not found")));

        Project project = projectRepository.findById(requestModel.getProjectId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project not found")));

        TeamLeader teamLeader = teamMember.getTeamLeader();
        if (teamLeader == null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Team Member is not assigned to any Team Leader"));
        }

        ProjectManager projectManager = project.getProjectManager();
        if (projectManager == null) {
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project has no Project Manager assigned"));
        }

        if (!teamMember.getProject().getId().equals(project.getId())) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Team Member is not assigned to the selected project"));
        }

        if (!teamLeader.getProject().getId().equals(project.getId())) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Team Leader is not assigned to the selected project"));
        }

        Task task = new Task();
        task.setTitle(requestModel.getTitle());
        task.setUserStoryDescription(requestModel.getUserStoryDescription());
        task.setAcceptanceCriteria(requestModel.getAcceptanceCriteria());
        task.setState(TaskState.BACKLOG);
        task.setPriority(requestModel.getPriority());
        task.setTeamMember(teamMember);
        task.setTeamLeader(teamLeader);
        task.setProjectManager(projectManager);
        task.setProject(project);

        Task savedTask = taskRepository.save(task);

        CreateTaskResponseModel responseModel = new CreateTaskResponseModel();
        BeanUtils.copyProperties(savedTask, responseModel);

        UserDTO teamMemberDTO = new UserDTO(teamMember.getUsername(), teamMember.getRole());
        UserDTO teamLeaderDTO = new UserDTO( teamLeader.getUsername(), teamLeader.getRole());
        UserDTO projectManagerDTO = new UserDTO(projectManager.getUsername(), projectManager.getRole());
        ProjectDTO projectDTO = new ProjectDTO(project.getId(), project.getName(), project.getDescription(), project.getResponsibleDepartmentName());

        responseModel.setTeamMember(teamMemberDTO);
        responseModel.setTeamLeader(teamLeaderDTO);
        responseModel.setProjectManager(projectManagerDTO);
        responseModel.setProject(projectDTO);

        return responseModel;
    }

    @Override
    @Transactional
    public UpdateTaskStateResponseModel updateTaskState(UpdateTaskStateRequestModel requestModel) {
        Task task = taskRepository.findById(requestModel.getTaskId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Task not found")));


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();


        boolean hasPermission = isUserAuthorizedToUpdateTask(currentUsername, task);
        if (!hasPermission) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "You are not authorized to update this task's state"));
        }

        validateStateTransition(task.getState(), requestModel.getNewState(), requestModel.getStateChangeReason());
        
        task.setState(requestModel.getNewState());
        Task updatedTask = taskRepository.save(task);
        
        return convertToResponseModel(updatedTask);
    }

    private boolean isUserAuthorizedToUpdateTask(String username, Task task) {
        if (task.getTeamMember().getUsername().equals(username)) {
            return true;
        }
        if (task.getTeamLeader().getUsername().equals(username)) {
            return true;
        }
        if (task.getProjectManager().getUsername().equals(username)) {
            return true;
        }
        
        return false;
    }

    private UpdateTaskStateResponseModel convertToResponseModel(Task task) {
        UpdateTaskStateResponseModel responseModel = new UpdateTaskStateResponseModel();
        
        responseModel.setUserStoryDescription(task.getUserStoryDescription());
        responseModel.setAcceptanceCriteria(task.getAcceptanceCriteria());
        responseModel.setState(task.getState());
        responseModel.setPriority(task.getPriority());
        
        List<CommentDTO> comments = new ArrayList<>();
        for (Comment comment : task.getComments()) {
            if (!comment.isDeleted()) {
                CommentDTO commentDTO = new CommentDTO(
                    comment.getContent(),
                    new UserDTO(
                        comment.getUser().getUsername(),
                        comment.getUser().getRole()
                    )
                );
                comments.add(commentDTO);
            }
        }
        responseModel.setComments(comments);

        List<AttachmentDTO> attachments = new ArrayList<>();
        for (Attachment attachment : task.getAttachments()) {
            AttachmentDTO attachmentDTO = new AttachmentDTO(
                attachment.getId(),
                attachment.getFilePath()
            );
            attachments.add(attachmentDTO);
        }
        responseModel.setAttachments(attachments);

        responseModel.setTeamMember(new UserDTO(
            task.getTeamMember().getUsername(),
            task.getTeamMember().getRole()
        ));
        
        return responseModel;
    }

    private void validateStateTransition(TaskState currentState, TaskState newState, String reason) {
        if (currentState == TaskState.COMPLETED) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "Tasks in COMPLETED state cannot be changed to any other state"));
        }

        if ((newState == TaskState.CANCELLED || newState == TaskState.BLOCKED) 
            && !StringUtils.hasText(reason)) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "Reason is required when changing state to CANCELLED or BLOCKED"));
        }

        switch (currentState) {
            case BACKLOG:
                if (newState != TaskState.IN_ANALYSIS && newState != TaskState.CANCELLED) {
                    throwInvalidTransitionException(currentState, newState);
                }
                break;

            case IN_ANALYSIS:
                if (newState != TaskState.BACKLOG && newState != TaskState.IN_DEVELOPMENT 
                    && newState != TaskState.BLOCKED && newState != TaskState.CANCELLED) {
                    throwInvalidTransitionException(currentState, newState);
                }
                break;

            case IN_DEVELOPMENT:
                if (newState != TaskState.IN_ANALYSIS && newState != TaskState.COMPLETED 
                    && newState != TaskState.BLOCKED && newState != TaskState.CANCELLED) {
                    throwInvalidTransitionException(currentState, newState);
                }
                break;

            case BLOCKED:
                if (newState != TaskState.IN_ANALYSIS && newState != TaskState.IN_DEVELOPMENT 
                    && newState != TaskState.CANCELLED) {
                    throwInvalidTransitionException(currentState, newState);
                }
                break;

            case CANCELLED:
                throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                    "Tasks in CANCELLED state cannot be changed to any other state"));
        }
    }

    private void throwInvalidTransitionException(TaskState currentState, TaskState newState) {
        throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
            String.format("Invalid state transition from %s to %s", currentState, newState)));
    }

    @Override
    @Transactional
    public AddCommentResponseModel addComment(AddCommentRequestModel requestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User not found")));

        Task task = taskRepository.findById(requestModel.getTaskId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Task not found")));

        boolean isUserInProject = isUserInProject(currentUser, task);
        if (!isUserInProject) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "You can only comment on tasks within your project"));
        }

        Comment comment = new Comment();
        comment.setContent(requestModel.getContent());
        comment.setUser(currentUser);

        Comment savedComment = commentRepository.save(comment);
        task.getComments().add(savedComment);
        taskRepository.save(task);

        return new AddCommentResponseModel(
            savedComment.getContent(),
            new UserDTO(
                currentUser.getUsername(),
                currentUser.getRole()
            ),
            task.getId()
        );
    }

    private boolean isUserInProject(User user, Task task) {
        //Long projectId = task.getProject().getId();
        if (task.getProjectManager().getId().equals(user.getId())) {
            return true;
        }
        if (task.getTeamLeader().getId().equals(user.getId())) {
            return true;
        }
        if (task.getTeamMember().getId().equals(user.getId())) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void deleteComment(DeleteCommentRequestModel requestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        User currentUser = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "User not found")));

        Comment comment = commentRepository.findByIdAndNotDeleted(requestModel.getCommentId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Comment not found or already deleted")));

        if (!comment.getUser().getId().equals(currentUser.getId())) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "You can only delete your own comments"));
        }

        commentRepository.softDelete(comment.getId());
    }
}
