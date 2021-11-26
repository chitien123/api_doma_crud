package com.api.crud.entity;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.seasar.doma.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "STUDENTS")
public class Students {
    @Id
    @Column(name = "ID")
    @NotNull
    public Integer id;
    @Column(name = "FullName")
    @Size(min = 5, max = 12)
    public String fullName;
    @Column(name = "Age")
    public Integer age;
    @Column(name = "Gender")
    @Size(min = 4,max = 6)
    public String gender;
    @Column(name = "Email")
    @Email
    public String email;
    @Column(name = "Phone")
    @Size(min = 10, max = 10)
    public String phone;
    @Column(name = "TeacherID")
    public Integer teacherId;

    public Students() {
    }

    public Students(Integer id, String fullName, Integer age, String gender, String email, String phone, Integer teacherId) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.teacherId = teacherId;
    }
}
