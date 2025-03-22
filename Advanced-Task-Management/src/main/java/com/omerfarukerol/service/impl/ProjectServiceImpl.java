package com.omerfarukerol.service.impl;

import com.omerfarukerol.entities.Project;
import com.omerfarukerol.entities.ProjectManager;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.enums.ProjectState;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.*;
import com.omerfarukerol.repository.ProjectManagerRepository;
import com.omerfarukerol.repository.ProjectRepository;
import com.omerfarukerol.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjectServiceImpl implements IProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectManagerRepository projectManagerRepository;

    @Override
    @Transactional
    public CreateProjectResponseModel createProject(CreateProjectRequestModel requestModel) {
        ProjectManager projectManager = projectManagerRepository.findById(requestModel.getProjectManagerId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project Manager not found")));

        Project project = new Project();
        project.setName(requestModel.getName());
        project.setDescription(requestModel.getDescription());
        project.setResponsibleDepartmentName(requestModel.getResponsibleDepartmentName());
        project.setProjectManager(projectManager);

        Project savedProject = projectRepository.save(project);

        CreateProjectResponseModel responseModel = new CreateProjectResponseModel();
        BeanUtils.copyProperties(savedProject, responseModel);

        ProjectManagerDTO projectManagerDTO = new ProjectManagerDTO();
        projectManagerDTO.setUsername(projectManager.getUsername());
        projectManagerDTO.setRole(projectManager.getRole().name());
        
        responseModel.setProjectManager(projectManagerDTO);

        return responseModel;
    }

    @Override
    @Transactional
    public UpdateProjectResponseModel updateProject(UpdateProjectRequestModel requestModel) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        Project project = projectRepository.findById(requestModel.getProjectId())
                .orElseThrow(() -> new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, "Project not found")));

        if (!project.getProjectManager().getUsername().equals(currentUsername)) {
            throw new BaseException(new ErrorMessage(MessageType.GENERAL_EXCEPTION, 
                "Only the project manager can update the project"));
        }

        if (requestModel.getTitle() != null) {
            project.setName(requestModel.getTitle());
        }
        if (requestModel.getDescription() != null) {
            project.setDescription(requestModel.getDescription());
        }
        if (requestModel.getResponsibleDepartmentName() != null) {
            project.setResponsibleDepartmentName(requestModel.getResponsibleDepartmentName());
        }
        if (requestModel.getStatus() != null) {
            validateStateTransition(project.getStatus(), requestModel.getStatus());
            project.setStatus(requestModel.getStatus());
        }

        Project updatedProject = projectRepository.save(project);

        UpdateProjectResponseModel responseModel = new UpdateProjectResponseModel();
        responseModel.setId(updatedProject.getId());
        responseModel.setName(updatedProject.getName());
        responseModel.setDescription(updatedProject.getDescription());
        responseModel.setStatus(updatedProject.getStatus());

        ProjectManagerDTO projectManagerDTO = new ProjectManagerDTO();
        projectManagerDTO.setUsername(updatedProject.getProjectManager().getUsername());
        projectManagerDTO.setRole(updatedProject.getProjectManager().getRole().name());
        responseModel.setProjectManager(projectManagerDTO);

        return responseModel;
    }

    private void validateStateTransition(ProjectState currentState, ProjectState newState) {
        if (currentState == ProjectState.COMPLETED || currentState == ProjectState.CANCELLED) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_STATE, 
                String.format("Cannot change project state from %s to any other state", currentState)));
        }

        if (currentState == ProjectState.IN_PROGRESS &&
            (newState != ProjectState.COMPLETED && newState != ProjectState.CANCELLED)) {
            throw new BaseException(new ErrorMessage(MessageType.INVALID_STATE,
                String.format("Invalid state transition from %s to %s. Project can only be COMPLETED or CANCELLED from IN_PROGRESS state", 
                    currentState, newState)));
        }
    }
} 