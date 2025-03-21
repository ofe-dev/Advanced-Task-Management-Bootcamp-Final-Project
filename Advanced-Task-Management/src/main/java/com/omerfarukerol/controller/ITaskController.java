package com.omerfarukerol.controller;

import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.RootResponse;

public interface ITaskController {
    RootResponse<CreateTaskResponseModel> createTask(CreateTaskRequestModel request);
} 