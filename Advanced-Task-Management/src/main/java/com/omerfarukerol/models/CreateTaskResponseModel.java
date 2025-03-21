package com.omerfarukerol.models;

import com.omerfarukerol.enums.TaskPriority;
import com.omerfarukerol.enums.TaskState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskResponseModel {
    private Long id;
    private String userStoryDescription;
    private String acceptanceCriteria;
    private TaskState state;
    private TaskPriority priority;
    private UserDTO teamMember;
    private UserDTO teamLeader;
    private UserDTO projectManager;
    private ProjectDTO project;
} 