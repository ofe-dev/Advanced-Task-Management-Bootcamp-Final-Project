package com.omerfarukerol.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "projects")
public class Project extends BaseEntity{


    @Column(nullable = false)
    private String name;

    @Column(length = 1024)
    private String description;


    @OneToOne
    @JoinColumn(name = "project_manager_id", nullable = false, unique = true)
    private ProjectManager projectManager;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamLeader> teamLeaders = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TeamMember> teamMembers = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Task> tasks = new ArrayList<>();
}
