package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class ClientEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Client client;

    @Embedded
    private Email email;

    public ClientEmail() {

    }

    public ClientEmail(Long id, Client client, String email) {
        this.id = id;
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public Email getEmail() {
        return email;
    }
}
