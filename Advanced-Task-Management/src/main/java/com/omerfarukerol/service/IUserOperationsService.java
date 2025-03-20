package com.omerfarukerol.service;

import com.omerfarukerol.models.CreateUserRequestModel;
import com.omerfarukerol.models.CreateUserResponseModel;

public interface IUserOperationsService {
    public CreateUserResponseModel createUser(CreateUserRequestModel requestModel);
}
