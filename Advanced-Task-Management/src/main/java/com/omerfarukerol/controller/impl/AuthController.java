package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IAuthController;
import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;
import com.omerfarukerol.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements IAuthController {

    @Autowired
    private IAuthenticationService authService;

    @PostMapping("/authenticate")
    @Override
    public AuthResponseModel authenticate(@Valid @RequestBody AuthRequestModel request) {
        return authService.authenticate(request);
    }

}
