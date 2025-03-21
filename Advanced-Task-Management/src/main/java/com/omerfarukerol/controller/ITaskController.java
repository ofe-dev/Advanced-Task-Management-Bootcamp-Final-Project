package com.omerfarukerol.controller;

import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.UpdateTaskStateRequestModel;
import com.omerfarukerol.models.UpdateTaskStateResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.models.AddCommentRequestModel;
import com.omerfarukerol.models.AddCommentResponseModel;

public interface ITaskController {
    RootResponse<CreateTaskResponseModel> createTask(CreateTaskRequestModel request);
    RootResponse<UpdateTaskStateResponseModel> updateTaskState(UpdateTaskStateRequestModel request);
    RootResponse<AddCommentResponseModel> addComment(AddCommentRequestModel request);
} 