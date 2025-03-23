package com.omerfarukerol.models;

import com.omerfarukerol.enums.TaskPriority;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskPriorityRequest {
    
    @NotNull(message = "Task ID cannot be null")
    private Long taskId;
    
    @NotNull(message = "New priority cannot be null")
    private TaskPriority newPriority;
} 