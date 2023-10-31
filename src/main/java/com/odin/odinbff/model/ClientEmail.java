package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity

public class ClientEmail {

    @EmbeddedId
    private final Pk id;
    private final Boolean isMain;

    public ClientEmail(final Client client, final Email email, final Boolean isMain) {
        this.id = new Pk(client, email);
        this.isMain = isMain;
    }

    public Client getClient() {
        return id.client;
    }

    public Email getEmail() {
        return id.email;
    }

    public Boolean isMain() {
        return isMain;
    }

    @Embeddable
    private static class Pk {

        @ManyToOne(optional = false)
        private final Client client;

        @Embedded
        private final Email email;

        private Pk(final Client client, final Email email) {
            this.client = client;
            this.email = email;
        }
    }
}
