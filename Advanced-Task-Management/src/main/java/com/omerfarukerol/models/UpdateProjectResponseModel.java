package com.omerfarukerol.models;

import com.omerfarukerol.enums.ProjectState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProjectResponseModel {
    private Long id;
    private String name;
    private String description;
    private String responsibleDepartmentName;
    private ProjectState status;
    private ProjectManagerDTO projectManager;
} 