package com.omerfarukerol.models;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestModel {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
