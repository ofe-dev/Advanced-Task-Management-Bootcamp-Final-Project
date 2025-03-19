package com.omerfarukerol.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String filePath;
}
