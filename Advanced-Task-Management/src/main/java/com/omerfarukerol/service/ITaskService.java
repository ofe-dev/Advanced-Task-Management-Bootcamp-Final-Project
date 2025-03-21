package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.UpdateTaskStateRequestModel;
import com.omerfarukerol.models.UpdateTaskStateResponseModel;

public interface ITaskService {
    CreateTaskResponseModel createTask(CreateTaskRequestModel requestModel);
    UpdateTaskStateResponseModel updateTaskState(UpdateTaskStateRequestModel requestModel);
}
