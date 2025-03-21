package com.omerfarukerol.models;

import com.omerfarukerol.enums.TaskPriority;
import com.omerfarukerol.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStateResponseModel {
    private String userStoryDescription;
    private String acceptanceCriteria;
    private TaskState state;
    private TaskPriority priority;
    private List<CommentDTO> comments;
    private List<AttachmentDTO> attachments;
    private UserDTO teamMember;
} 