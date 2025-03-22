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
public class TaskDetailResponseModel {
    private String title;
    private String userStoryDescription;
    private String acceptanceCriteria;
    private TaskState state;
    private TaskPriority priority;
    private String teamMemberUsername;
    private String teamLeaderUsername;
    private int commentCount;
    private int attachmentCount;
    private List<CommentDTO> comments;
    private List<AttachmentDTO> attachments;
} 