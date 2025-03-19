package com.omerfarukerol.controller;

import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;
import com.omerfarukerol.models.RootResponse;

public interface IAuthController {

    public RootResponse<AuthResponseModel> authenticate(AuthRequestModel request);

}
