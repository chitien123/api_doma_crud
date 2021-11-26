package com.api.crud.entity;

import lombok.*;
import org.seasar.doma.*;
import org.seasar.doma.jdbc.entity.NamingType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Getter
@Setter
@Entity(naming = NamingType.SNAKE_UPPER_CASE)
@Table(name = "Users")
public class Users {
    @Id
    @Column(name = "ID")
    public Integer id;
    @Column(name = "UserName")
    @Size(min = 3,max = 12,message = "User name must be more than three characters and less than twelve characters")
    @NotBlank(message = "User name cannot be left blank" )
    public String userName;
    @Column(name = "Password")
    @Size(min = 3,max = 12,message = "Password must be more than three characters and less than twelve characters")
    @NotBlank(message = "User name cannot be left blank" )
    public String password;

    public Users() {
    }

    public Users(Integer id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
