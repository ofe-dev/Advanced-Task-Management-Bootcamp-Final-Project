package com.omerfarukerol.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_deleted", columnDefinition = "boolean default false")
    private boolean isDeleted = false;
}