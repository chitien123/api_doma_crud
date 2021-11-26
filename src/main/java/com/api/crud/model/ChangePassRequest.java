package com.api.crud.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangePassRequest {
    String currentPassword;
    String newPassword;

    public ChangePassRequest() {
    }

    public ChangePassRequest(String currentPassword, String newPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

}
