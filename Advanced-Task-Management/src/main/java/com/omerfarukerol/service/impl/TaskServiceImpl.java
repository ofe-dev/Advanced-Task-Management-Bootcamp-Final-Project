package com.omerfarukerol.service.impl;

import com.omerfarukerol.entities.*;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.enums.TaskState;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.ProjectDTO;
import com.omerfarukerol.models.UserDTO;
import com.omerfarukerol.repository.*;
import com.omerfarukerol.service.ITaskService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TaskServiceImpl implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TeamMemberRepository teamMemberRepository;

    @Autowired
    private ProjectRepository projectRepository;

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

        UserDTO teamMemberDTO = new UserDTO(teamMember.getId(), teamMember.getUsername(), teamMember.getRole());
        UserDTO teamLeaderDTO = new UserDTO(teamLeader.getId(), teamLeader.getUsername(), teamLeader.getRole());
        UserDTO projectManagerDTO = new UserDTO(projectManager.getId(), projectManager.getUsername(), projectManager.getRole());
        ProjectDTO projectDTO = new ProjectDTO(project.getId(), project.getName(), project.getDescription());

        responseModel.setTeamMember(teamMemberDTO);
        responseModel.setTeamLeader(teamLeaderDTO);
        responseModel.setProjectManager(projectManagerDTO);
        responseModel.setProject(projectDTO);

        return responseModel;
    }
}
