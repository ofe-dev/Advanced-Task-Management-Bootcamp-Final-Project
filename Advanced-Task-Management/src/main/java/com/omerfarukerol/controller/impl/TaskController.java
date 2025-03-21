package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.ITaskController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.UpdateTaskStateRequestModel;
import com.omerfarukerol.models.UpdateTaskStateResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/task")
public class TaskController extends RootBaseController implements ITaskController {

    @Autowired
    private ITaskService taskService;

    @PostMapping("/createtask")
    @PreAuthorize("hasRole('ROLE_PROJECT_MANAGER')")
    @Override
    public RootResponse<CreateTaskResponseModel> createTask(@Valid @RequestBody CreateTaskRequestModel request) {
        return ok(taskService.createTask(request));
    }

    @PutMapping("/UpdateState")
    @PreAuthorize("hasAnyRole('ROLE_PROJECT_MANAGER', 'ROLE_TEAM_LEADER', 'ROLE_TEAM_MEMBER')")
    @Override
    public RootResponse<UpdateTaskStateResponseModel> updateTaskState(
            @Valid @RequestBody UpdateTaskStateRequestModel request) {
        return ok(taskService.updateTaskState(request));
    }
} 