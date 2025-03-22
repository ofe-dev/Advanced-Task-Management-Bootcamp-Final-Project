package com.omerfarukerol.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;

public enum ProjectState {
    IN_PROGRESS("IN_PROGRESS"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED");

    private final String state;

    ProjectState(String state) {
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return state;
    }

    @JsonCreator
    public static ProjectState fromString(String value) {
        if (value == null) {
            return null;
        }
        
        for (ProjectState state : ProjectState.values()) {
            if (state.state.equalsIgnoreCase(value)) {
                return state;
            }
        }
        throw new BaseException(new ErrorMessage(MessageType.INVALID_STATE, 
            String.format("Invalid project state: %s. Valid states are: IN_PROGRESS, CANCELLED, COMPLETED", value)));
    }
}
