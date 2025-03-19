package com.omerfarukerol.entities;

import com.omerfarukerol.enums.RoleType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private RoleType role;
}
