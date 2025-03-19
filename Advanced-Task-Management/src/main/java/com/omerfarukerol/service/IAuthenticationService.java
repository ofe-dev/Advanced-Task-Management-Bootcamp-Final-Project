package com.omerfarukerol.service;

import com.omerfarukerol.models.AuthRequestModel;
import com.omerfarukerol.models.AuthResponseModel;

public interface IAuthenticationService {
    public AuthResponseModel authenticate(AuthRequestModel request);
}
