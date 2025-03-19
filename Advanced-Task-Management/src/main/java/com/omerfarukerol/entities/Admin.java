package com.omerfarukerol.entities;

import com.omerfarukerol.enums.RoleType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class Admin extends User {

    public Admin() {
        setRole(RoleType.ROLE_ADMIN);
    }
}
