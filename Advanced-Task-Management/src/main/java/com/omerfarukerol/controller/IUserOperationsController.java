package com.omerfarukerol.controller;

import com.omerfarukerol.models.*;

public interface IUserOperationsController {

    public RootResponse<CreateUserResponseModel> createUser(CreateUserRequestModel request);
    public RootResponse<ChangePasswordResponseModel> changePassword(ChangePasswordRequestModel request);

}
