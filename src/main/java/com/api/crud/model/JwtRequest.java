package com.api.crud.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Getter
@Setter
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private Integer id;
    @Size(min = 3,max = 12,message = "User name must be more than three characters and less than twelve characters")
    @NotBlank(message = "User name cannot be left blank" )
    private String username;
    @Size(min = 3,max = 12,message = "Password must be more than three characters and less than twelve characters")
    @NotBlank(message = "User name cannot be left blank" )
    private String password;

    //need default constructor for JSON Parsing

    public JwtRequest() {
    }

    public JwtRequest(Integer id,String username, String password) {
        this.id=id;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
