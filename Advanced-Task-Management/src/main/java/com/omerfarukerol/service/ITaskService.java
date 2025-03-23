package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.UpdateTaskStateRequestModel;
import com.omerfarukerol.models.UpdateTaskStateResponseModel;
import com.omerfarukerol.models.AddCommentRequestModel;
import com.omerfarukerol.models.AddCommentResponseModel;
import com.omerfarukerol.models.DeleteCommentRequestModel;
import com.omerfarukerol.models.AttachFileRequest;
import com.omerfarukerol.models.AttachmentDTO;

public interface ITaskService {
    CreateTaskResponseModel createTask(CreateTaskRequestModel requestModel);
    UpdateTaskStateResponseModel updateTaskState(UpdateTaskStateRequestModel requestModel);
    AddCommentResponseModel addComment(AddCommentRequestModel requestModel);
    void deleteComment(DeleteCommentRequestModel requestModel);
    AttachmentDTO attachFile(AttachFileRequest request);
}
