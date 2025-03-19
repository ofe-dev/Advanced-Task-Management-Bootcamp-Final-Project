package com.omerfarukerol.controller;

import com.omerfarukerol.models.RootResponse;

public class RootBaseController {

    public <T> RootResponse<T> ok(T payload){
        return RootResponse.ok(payload);
    }

    public <T> RootResponse<T> error(String errorMessage){
        return RootResponse.error(errorMessage);
    }
}
