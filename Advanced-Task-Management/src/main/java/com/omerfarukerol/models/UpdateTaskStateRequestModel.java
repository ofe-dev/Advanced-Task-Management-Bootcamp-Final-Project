package com.omerfarukerol.models;

import com.omerfarukerol.enums.TaskState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTaskStateRequestModel {
    
    @NotNull(message = "Task ID cannot be null")
    private Long taskId;
    
    @NotNull(message = "Task state cannot be null")
    private TaskState newState;
    
    private String stateChangeReason;
} 