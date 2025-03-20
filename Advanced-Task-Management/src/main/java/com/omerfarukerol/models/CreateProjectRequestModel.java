package com.omerfarukerol.models;

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
public class CreateProjectRequestModel {
    
    @NotBlank(message = "Project name cannot be empty")
    private String name;
    
    @NotBlank(message = "Project description cannot be empty")
    private String description;
    
    @NotNull(message = "Project manager ID cannot be null")
    private Long projectManagerId;
} 