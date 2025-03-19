package com.omerfarukerol.entities;

import com.omerfarukerol.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "team_members")
public class TeamMember extends User {

    @ManyToOne
    @JoinColumn(name = "team_leader_id", nullable = false)
    private TeamLeader teamLeader;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public TeamMember() {
        setRole(RoleType.ROLE_TEAM_MEMBER);
    }
}
