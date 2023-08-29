package com.odin.odinbff.database;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String familyName;

    @Embedded
    @NotBlank
    private Cpf cpf;

    public Client(Long id, String firstName, String familyName, Cpf cpf) {
        this.id = id;
        this.firstName = firstName;
        this.familyName = familyName;
        this.cpf = cpf;
    }
}
