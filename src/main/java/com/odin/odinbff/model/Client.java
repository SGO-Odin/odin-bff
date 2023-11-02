package com.odin.odinbff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @Embedded
    @NotBlank
    private final Cpf cpf;

    private final String rg;

    @ManyToOne(optional = false)
    private final Address address;

    @CreationTimestamp
    private final LocalDateTime createdOn;

    @UpdateTimestamp
    private final LocalDateTime updatedOn;

    @OneToMany(mappedBy = "id.client", fetch = FetchType.LAZY)
    private final Set<ClientEmail> emails = new HashSet<>();

    @OneToMany(mappedBy = "id.client")
    private final Set<ClientPhone> phones = new HashSet<>();

    public Client(String firstName,
                  String lastName,
                  Cpf cpf,
                  String rg, Address address) {
        this(null, firstName, lastName, cpf, rg, address, LocalDateTime.now(), LocalDateTime.now());
    }

    public Client(Long id,
                  String firstName,
                  String lastName,
                  Cpf cpf,
                  String rg, Address address, LocalDateTime createdOn, LocalDateTime updatedOn) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.rg = rg;
        this.address = address;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public void addEmail(final ClientEmail email) {
        emails.add(email);
    }

    public void addPhone(final ClientPhone phone) {
        phones.add(phone);
    }

    public Long getId() {
        return id;
    }
}
