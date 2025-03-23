package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IAuthController;
import com.omerfarukerol.controller.RootBaseController;
import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.service.IAuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController extends RootBaseController implements IAuthController {

    @Autowired
    private IAuthenticationService authenticationService;

    @PostMapping("/authenticate")
    @Override
    public RootResponse<AuthResponseModel> authenticate(@Valid @RequestBody AuthRequestModel request) {
        return ok(authenticationService.authenticate(request));
    }
}
