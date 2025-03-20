package com.omerfarukerol.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "attachments")
public class Attachment extends BaseEntity {

    @Column(nullable = false)
    private String filePath;
}
