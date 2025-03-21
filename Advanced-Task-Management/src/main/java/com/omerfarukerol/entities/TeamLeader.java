package com.omerfarukerol.entities;

import com.omerfarukerol.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "team_leaders")
public class TeamLeader extends User {

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    public TeamLeader() {
        setRole(RoleType.ROLE_TEAM_LEADER);
    }
}