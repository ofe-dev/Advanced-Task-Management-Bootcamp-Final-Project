package com.omerfarukerol.controller.impl;

import com.omerfarukerol.controller.IUserOperationsController;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.models.CreateUserRequestModel;
import com.omerfarukerol.models.CreateUserResponseModel;
import com.omerfarukerol.models.RootResponse;
import com.omerfarukerol.models.ChangePasswordRequestModel;
import com.omerfarukerol.models.ChangePasswordResponseModel;
import com.omerfarukerol.service.IUserOperationsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserOperationsController implements IUserOperationsController {

    @Autowired
    private IUserOperationsService userOperationsService;

    @PostMapping("/CreateUser")
    @Override
    public RootResponse<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel request) {
        return RootResponse.ok(userOperationsService.createUser(request));
    }

    @PostMapping("/ChangePassword")
    public RootResponse<ChangePasswordResponseModel> changePassword(@Valid @RequestBody ChangePasswordRequestModel request) {
        return RootResponse.ok(userOperationsService.changePassword(request));
    }
}
