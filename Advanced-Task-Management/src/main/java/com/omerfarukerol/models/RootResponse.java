package com.omerfarukerol.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RootResponse<T> {

    private boolean isSuccess;

    private T data;

    private String errorMessage;

    public static <T> RootResponse<T> ok(T data){
        RootResponse<T> rootResponse = new RootResponse<>();
        rootResponse.setSuccess(true);
        rootResponse.setData(data);
        rootResponse.setErrorMessage(null);
        return rootResponse;
    }

    public static <T> RootResponse<T> error(String errorMessage){
        RootResponse<T> rootResponse = new RootResponse<>();
        rootResponse.setSuccess(false);
        rootResponse.setData(null);
        rootResponse.setErrorMessage(errorMessage);
        return rootResponse;
    }
}
