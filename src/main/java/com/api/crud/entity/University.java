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
@Table(name = "UNIVERSITY")
public class University {
    @Id
    @Column(name = "ID")
    @NotNull
    public Integer id;
    @Column(name = "FullName")
    @Size(min = 6, max = 30)
    public String fullName;
    @Column(name = "Locations")
    @Size(min = 6, max = 30)
    public String locations;

    public University() {
    }

    public University(Integer id, String fullName, String locations) {
        this.id = id;
        this.fullName = fullName;
        this.locations = locations;
    }
}
