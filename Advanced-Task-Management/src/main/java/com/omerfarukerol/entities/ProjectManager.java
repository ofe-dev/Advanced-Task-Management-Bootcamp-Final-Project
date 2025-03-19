package com.omerfarukerol.entities;


import com.omerfarukerol.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "project_managers")
public class ProjectManager extends User {

    @OneToOne(mappedBy = "projectManager")
    private Project project;

    public ProjectManager() {
        setRole(RoleType.ROLE_PROJECT_MANAGER);
    }
}
