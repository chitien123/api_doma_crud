package com.api.crud.entity;

import lombok.Getter;
import lombok.Setter;
import org.seasar.doma.*;

import java.time.LocalDate;

@Setter
@Getter
@Entity
@Table(name="refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(sequence = "refresh_token_ceq")
    @Column(name="Id")
    Integer id;

    @Column(name="Token")
    String token;

    @Column(name="Expiry_DT")
    LocalDate expiryDate;

    @Column(name="UserId")
    Integer userId;

}
