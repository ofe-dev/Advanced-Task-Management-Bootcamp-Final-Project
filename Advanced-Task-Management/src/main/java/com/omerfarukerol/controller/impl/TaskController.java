package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.ITaskController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.CreateTaskRequestModel;
import com.omerfarukerol.models.CreateTaskResponseModel;
import com.omerfarukerol.models.UpdateTaskStateRequestModel;
import com.omerfarukerol.models.UpdateTaskStateResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.models.AddCommentRequestModel;
import com.omerfarukerol.models.AddCommentResponseModel;
import com.omerfarukerol.models.DeleteCommentRequestModel;
import com.omerfarukerol.models.AttachFileRequest;
import com.omerfarukerol.models.AttachmentDTO;
import com.omerfarukerol.service.ITaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @PostMapping("/AddComment")
    @PreAuthorize("hasAnyRole('ROLE_PROJECT_MANAGER', 'ROLE_TEAM_LEADER', 'ROLE_TEAM_MEMBER')")
    @Override
    public RootResponse<AddCommentResponseModel> addComment(@Valid @RequestBody AddCommentRequestModel request) {
        return ok(taskService.addComment(request));
    }

    @DeleteMapping("/DeleteComment")
    @PreAuthorize("hasAnyRole('ROLE_PROJECT_MANAGER', 'ROLE_TEAM_LEADER', 'ROLE_TEAM_MEMBER')")
    @Override
    public RootResponse<Void> deleteComment(@Valid @RequestBody DeleteCommentRequestModel request) {
        taskService.deleteComment(request);
        return ok(null);
    }

    @PostMapping("/AttachFile")
    @PreAuthorize("hasAnyRole('ROLE_PROJECT_MANAGER', 'ROLE_TEAM_LEADER', 'ROLE_TEAM_MEMBER')")
    @Override
    public RootResponse<AttachmentDTO> attachFile(@Valid @ModelAttribute AttachFileRequest request) {
        return ok(taskService.attachFile(request));
    }
} 