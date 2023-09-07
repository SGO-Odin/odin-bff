package com.odin.odinbff.data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

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
        this(firstName, familyName, cpf);
        this.id = id;
    }

    public Client(String firstName, String familyName, Cpf cpf) {
        this.firstName = firstName;
        this.familyName = familyName;
        this.cpf = cpf;
    }
}
