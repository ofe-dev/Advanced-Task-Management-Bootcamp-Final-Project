package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IProjectController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.UpdateProjectRequestModel;
import com.omerfarukerol.models.UpdateProjectResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.service.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends RootBaseController implements IProjectController {

    @Autowired
    private IProjectService projectService;

    @PostMapping("/CreateProject")
    @Override
    public RootResponse<CreateProjectResponseModel> createProject(@Valid @RequestBody CreateProjectRequestModel request) {
        return ok(projectService.createProject(request));
    }

    @PutMapping("/UpdateProject")
    @PreAuthorize("hasRole('ROLE_PROJECT_MANAGER')")
    @Override
    public RootResponse<UpdateProjectResponseModel> updateProject(@Valid @RequestBody UpdateProjectRequestModel request) {
        return ok(projectService.updateProject(request));
    }
} 