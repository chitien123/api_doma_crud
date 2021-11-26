package com.api.crud.model;

import lombok.Getter;

@Getter
public class SuccessMessage {
    private boolean success;
    private String message;

    public SuccessMessage(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

}
