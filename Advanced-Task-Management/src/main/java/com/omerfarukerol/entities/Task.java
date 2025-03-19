package com.omerfarukerol.entities;

import com.omerfarukerol.enums.RoleType;
import com.omerfarukerol.enums.TaskPriority;
import com.omerfarukerol.enums.TaskState;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userStoryDescription;

    @Column(nullable = false)
    private String acceptanceCriteria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskState state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskPriority priority;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "task_id")
    private List<Attachment> attachments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "team_member_id", nullable = false)
    private TeamMember teamMember;

    @ManyToOne
    @JoinColumn(name = "team_leader_id", nullable = false)
    private TeamLeader teamLeader;

    @ManyToOne
    @JoinColumn(name = "project_manager_id", nullable = false)
    private ProjectManager projectManager;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;
}
