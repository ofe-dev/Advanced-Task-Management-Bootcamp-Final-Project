package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IProjectController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.service.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
} 