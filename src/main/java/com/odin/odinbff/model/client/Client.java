package com.odin.odinbff.model.client;

import com.odin.odinbff.model.*;
import com.odin.odinbff.model.address.Address;
import com.odin.odinbff.model.audit.HistoryLoggable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public final class Client extends HistoryLoggable<Client> implements HasLongId, Activatable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @Embedded
    @NotNull
    private final Cpf cpf;

    private final String rg;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final Address address;

    @CreationTimestamp
    private final LocalDateTime createdOn;

    @UpdateTimestamp
    private final LocalDateTime updatedOn;

    @OneToMany(mappedBy = "id.client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<ClientEmail> emails = new HashSet<>();

    @OneToMany(mappedBy = "id.client", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final Set<ClientPhone> phones = new HashSet<>();

    @Column(nullable = false)
    private Boolean isActive;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Client() {
        this(null, null, null, null, null, null, null, null);
    }

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
        this.isActive = true;
    }

    public void addEmail(final Email email, final Boolean isMain) {
        emails.add(new ClientEmail(this, email, false));
    }

    public void addPhone(final Phone phone, final Boolean isMain) {
        phones.add(new ClientPhone(this, phone, isMain));
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public String getRg() {
        return rg;
    }

    public Address getAddress() {
        return address;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public Set<ClientEmail> getEmails() {
        return Collections.unmodifiableSet(emails);
    }

    public Set<ClientPhone> getPhones() {
        return Collections.unmodifiableSet(phones);
    }

    @Override
    public void activate() {
        isActive = true;
    }

    @Override
    public void inactivate() {
        isActive = false;
    }

    @Override
    public Boolean isActive() {
        return isActive;
    }
}
