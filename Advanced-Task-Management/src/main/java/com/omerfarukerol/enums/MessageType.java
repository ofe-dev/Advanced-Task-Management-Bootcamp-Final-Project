package com.omerfarukerol.enums;

import lombok.Getter;

@Getter
public enum MessageType {

    NO_RECORD_EXIST("101,","No record found"),
    GENERAL_EXCEPTION("201","A general error occurred"),
    TOKEN_IS_EXPIRED("301","Token expired"),
    USERNAME_NOT_FOUND("401","Username can not find"),
    INVALID_ROLE_TYPE("102","Invalid role type"),
    INVALID_STATE("501","Invalid state");

    private String code;

    private String message;

    MessageType(String code, String message){
        this.code = code;
        this.message = message;
    }

}
