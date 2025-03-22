package com.omerfarukerol.models;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetProjectTasksRequest {
    
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
} 