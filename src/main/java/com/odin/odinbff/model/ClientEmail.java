package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class ClientEmail {

    @Id
    @ManyToOne(optional = false)
    private Client client;

    @Embedded
    @MapsId("email")
    private Email email;

    public ClientEmail() {

    }

    public ClientEmail(Client client, String email) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
