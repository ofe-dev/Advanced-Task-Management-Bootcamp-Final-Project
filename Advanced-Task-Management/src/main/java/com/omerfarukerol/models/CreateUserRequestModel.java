package com.omerfarukerol.models;

import com.omerfarukerol.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestModel {

    private String username;

    private String password;

    private RoleType role;

}
