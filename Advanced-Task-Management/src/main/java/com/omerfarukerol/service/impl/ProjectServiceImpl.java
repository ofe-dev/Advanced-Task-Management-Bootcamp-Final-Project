package com.omerfarukerol.service.impl;

import com.omerfarukerol.entities.Project;
import com.omerfarukerol.entities.ProjectManager;
import com.omerfarukerol.enums.MessageType;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;
import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.ProjectManagerDTO;
import com.omerfarukerol.repository.ProjectManagerRepository;
import com.omerfarukerol.repository.ProjectRepository;
import com.omerfarukerol.service.IProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        project.setProjectManager(projectManager);

        Project savedProject = projectRepository.save(project);

        CreateProjectResponseModel responseModel = new CreateProjectResponseModel();
        BeanUtils.copyProperties(savedProject, responseModel);

        ProjectManagerDTO projectManagerDTO = new ProjectManagerDTO();
        projectManagerDTO.setId(projectManager.getId());
        projectManagerDTO.setUsername(projectManager.getUsername());
        projectManagerDTO.setRole(projectManager.getRole().name());
        
        responseModel.setProjectManager(projectManagerDTO);

        return responseModel;
    }
} 