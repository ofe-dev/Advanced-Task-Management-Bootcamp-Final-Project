package com.omerfarukerol.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class RootResponse<T> {

    private boolean success;
    private T data;
    private String message;
    private Map<String, List<String>> validationErrors;

    public static <T> RootResponse<T> ok(T data) {
        RootResponse<T> rootResponse = new RootResponse<>();
        rootResponse.setSuccess(true);
        rootResponse.setData(data);
        rootResponse.setMessage(null);
        rootResponse.setValidationErrors(null);
        return rootResponse;
    }

    public static <T> RootResponse<T> error(String message) {
        RootResponse<T> rootResponse = new RootResponse<>();
        rootResponse.setSuccess(false);
        rootResponse.setData(null);
        rootResponse.setMessage(message);
        rootResponse.setValidationErrors(null);
        return rootResponse;
    }

    public static <T> RootResponse<T> validationError(Map<String, List<String>> validationErrors) {
        RootResponse<T> rootResponse = new RootResponse<>();
        rootResponse.setSuccess(false);
        rootResponse.setData(null);
        rootResponse.setMessage("Validation failed");
        rootResponse.setValidationErrors(validationErrors);
        return rootResponse;
    }
}
