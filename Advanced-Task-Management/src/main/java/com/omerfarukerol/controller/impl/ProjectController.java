package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IProjectController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateProjectRequestModel;
import com.omerfarukerol.models.CreateProjectResponseModel;
import com.omerfarukerol.models.UpdateProjectRequestModel;
import com.omerfarukerol.models.UpdateProjectResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.models.TaskDetailResponseModel;
import com.omerfarukerol.models.GetProjectTasksRequest;
import com.omerfarukerol.models.GetTeamTasksRequest;
import com.omerfarukerol.models.GetMemberTasksRequest;
import com.omerfarukerol.service.IProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
public class ProjectController extends RootBaseController implements IProjectController {

    @Autowired
    private IProjectService projectService;

    @PostMapping("/CreateProject")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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

    @GetMapping("/GetProjectTasks")
    @PreAuthorize("hasRole('ROLE_PROJECT_MANAGER')")
    public RootResponse<List<TaskDetailResponseModel>> getProjectTasks(@Valid @RequestBody GetProjectTasksRequest request) {
        return ok(projectService.getProjectTasks(request));
    }

    @GetMapping("/GetTeamTasks")
    @PreAuthorize("hasRole('TEAM_LEADER')")
    public ResponseEntity<List<TaskDetailResponseModel>> getTeamTasks(@Valid @RequestBody GetTeamTasksRequest request) {
        return ResponseEntity.ok(projectService.getTeamTasks(request));
    }

    @GetMapping("/GetMemberTasks")
    @PreAuthorize("hasRole('TEAM_MEMBER')")
    public ResponseEntity<List<TaskDetailResponseModel>> getMemberTasks(@Valid @RequestBody GetMemberTasksRequest request) {
        return ResponseEntity.ok(projectService.getMemberTasks(request));
    }
} 