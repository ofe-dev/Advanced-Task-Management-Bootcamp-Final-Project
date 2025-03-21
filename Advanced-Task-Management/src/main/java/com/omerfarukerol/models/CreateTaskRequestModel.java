package com.omerfarukerol.models;

import com.omerfarukerol.enums.TaskPriority;
import com.omerfarukerol.enums.TaskState;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskRequestModel {
    
    @NotBlank(message = "User story description cannot be empty")
    private String userStoryDescription;
    
    @NotBlank(message = "Acceptance criteria cannot be empty")
    private String acceptanceCriteria;
    
    @NotNull(message = "Task priority cannot be null")
    private TaskPriority priority;
    
    @NotNull(message = "Team member ID cannot be null")
    private Long teamMemberId;
    
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
} 