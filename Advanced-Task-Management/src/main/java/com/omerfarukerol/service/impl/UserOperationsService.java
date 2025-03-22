package com.omerfarukerol.service.impl;

import com.omerfarukerol.entities.*;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.enums.RoleType;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.*;
import com.omerfarukerol.repository.ProjectRepository;
import com.omerfarukerol.repository.UserRepository;
import com.omerfarukerol.service.IUserOperationsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserOperationsService implements IUserOperationsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public CreateUserResponseModel createUser(CreateUserRequestModel requestModel) {
        User user;

        switch (requestModel.getRole()) {
            case ROLE_ADMIN:
                user = new Admin();
                break;
            case ROLE_PROJECT_MANAGER:
                user = new ProjectManager();
                break;
            case ROLE_TEAM_LEADER:
                TeamLeader newTeamLeader = new TeamLeader();
                if (requestModel.getProjectId() != null) {
                    Project project = projectRepository.findById(requestModel.getProjectId())
                            .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project not found")));
                    newTeamLeader.setProject(project);
                }
                user = newTeamLeader;
                break;
            case ROLE_TEAM_MEMBER:
                TeamMember teamMember = new TeamMember();

                if (requestModel.getProjectId() == null) {
                    throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project ID is required for Team Member"));
                }
                Project project = projectRepository.findById(requestModel.getProjectId())
                        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project not found")));
                teamMember.setProject(project);

                if (requestModel.getTeamLeadId() == null) {
                    throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Team Leader ID is required for Team Member"));
                }

                User dbUser =  userRepository.findById(requestModel.getTeamLeadId())
                        .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Team Leader not found")));
                if (dbUser.getRole()!= RoleType.ROLE_TEAM_LEADER) {
                    throw new BaseException(new ErrorMessage(MessageType.INVALID_ROLE_TYPE, "Selected user is not a Team Leader"));
                }

                TeamLeader assignedTeamLeader = (TeamLeader) dbUser;
                if (!assignedTeamLeader.getProject().getId().equals(project.getId())) {
                    throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Team Leader is not assigned to the selected project"));
                }

                teamMember.setTeamLeader(assignedTeamLeader);
                user = teamMember;
                break;
            default:
                throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Invalid role"));
        }

        user.setUsername(requestModel.getUsername());
        user.setPassword(passwordEncoder.encode(requestModel.getPassword()));
        user.setRole(requestModel.getRole());

        User savedUser = userRepository.save(user);

        CreateUserResponseModel responseModel = new CreateUserResponseModel();
        BeanUtils.copyProperties(savedUser, responseModel);
        return responseModel;
    }

    @Override
    @Transactional
    public ChangePasswordResponseModel changePassword(ChangePasswordRequestModel request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.USERNAME_NOT_FOUND, username)));

        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "Current password is incorrect"));
        }
        if (!request.getNewPassword().equals(request.getNewPasswordConfirmation())) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, "New passwords do not match"));
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return new ChangePasswordResponseModel();
    }
}
