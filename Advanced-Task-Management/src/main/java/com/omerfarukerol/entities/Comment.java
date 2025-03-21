package com.omerfarukerol.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
