package com.omerfarukerol.models;

import com.omerfarukerol.enums.ProjectState;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectRequestModel {
    
    @NotNull(message = "Project ID cannot be null")
    private Long projectId;
    
    private String title;
    
    private String description;
    
    private ProjectState status;
} 