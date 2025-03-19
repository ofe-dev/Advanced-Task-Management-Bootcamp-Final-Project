package com.omerfarukerol.controller;

import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;

public interface IAuthController {

    public AuthResponseModel authenticate(AuthRequestModel request);

}
