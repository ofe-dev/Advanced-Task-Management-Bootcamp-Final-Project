package com.omerfarukerol.service;

import com.omerfarukerol.models.ChangePasswordRequestModel;
import com.omerfarukerol.models.ChangePasswordResponseModel;
import com.omerfarukerol.models.CreateUserRequestModel;
import com.omerfarukerol.models.CreateUserResponseModel;

public interface IUserOperationsService {
    public CreateUserResponseModel createUser(CreateUserRequestModel requestModel);
    public CreateUserResponseModel createAdmin(CreateUserRequestModel requestModel);
    public ChangePasswordResponseModel changePassword(ChangePasswordRequestModel request);
}
