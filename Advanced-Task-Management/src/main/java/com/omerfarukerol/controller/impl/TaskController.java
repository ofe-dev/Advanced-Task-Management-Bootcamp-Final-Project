package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.ITaskController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController extends RootBaseController implements ITaskController {

    @Autowired
    private ITaskService taskService;

    @PostMapping("/createtask")
    @Override
    public RootResponse<CreateTaskResponseModel> createTask(@Valid @RequestBody CreateTaskRequestModel request) {
        return ok(taskService.createTask(request));
    }
} 