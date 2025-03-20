package com.omerfarukerol.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProjectResponseModel {
    private Long id;
    private String name;
    private String description;
    private ProjectManagerDTO projectManager;
} 