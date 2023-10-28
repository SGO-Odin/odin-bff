package com.odin.odinbff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.internal.util.stereotypes.Lazy;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Embedded
    @NotBlank
    private Cpf cpf;

    private String rg;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Set<ClientEmail> emails = new HashSet<>();

    private final Set<String> phones = new HashSet<>();

    public Client(Long id,
                  String firstName,
                  String lastName,
                  Cpf cpf,
                  String rg) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.rg = rg;
    }

    public void setEmails(Set<ClientEmail> emails) {
        this.emails = emails;
    }

    public Long getId() {
        return id;
    }
}
