package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;

public interface ITaskService {
    CreateTaskResponseModel createTask(CreateTaskRequestModel requestModel);
}
