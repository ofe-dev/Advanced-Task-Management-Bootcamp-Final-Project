package com.omerfarukerol.models;


import com.omerfarukerol.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserResponseModel {

    private String username;

    private RoleType role;

}
