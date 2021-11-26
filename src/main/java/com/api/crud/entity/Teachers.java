package com.api.crud.entity;

import lombok.*;
import org.seasar.doma.Column;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Table;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@Table(name = "TEACHER")
public class Teachers {
    @Id
    @Column(name = "ID")
    @NotNull
    public Integer id;
    @Column(name = "FullName")
    @Size(min = 6, max = 8)
    public String fullName;
    @Column(name = "Age")
    public Integer age;
    @Column(name = "Gender")
    @Size(min = 4, max = 6)
    public String gender;
    @Column(name = "ClassName")
    @Size(min = 6, max = 30)
    public String className;
    @Column(name = "UniversityID")
    public Integer universityId;

    public Teachers() {
    }

    public Teachers(Integer id, String fullName, Integer age, String gender, String className, Integer universityId) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.className = className;
        this.universityId = universityId;
    }
}
