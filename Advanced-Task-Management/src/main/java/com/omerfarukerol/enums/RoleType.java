package com.omerfarukerol.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.omerfarukerol.exception.BaseException;
import com.omerfarukerol.exception.ErrorMessage;

public enum RoleType {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PROJECT_MANAGER("ROLE_PROJECT_MANAGER"),
    ROLE_TEAM_LEADER("ROLE_TEAM_LEADER"),
    ROLE_TEAM_MEMBER("ROLE_TEAM_MEMBER");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    @JsonValue
    public String getRole() {
        return role;
    }

    @JsonCreator
    public static RoleType fromString(String value) {
        for (RoleType role : RoleType.values()) {
            if (role.role.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new BaseException(new ErrorMessage(MessageType.INVALID_ROLE_TYPE,null));
    }

}
