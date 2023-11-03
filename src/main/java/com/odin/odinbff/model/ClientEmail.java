package com.odin.odinbff.model;

import jakarta.persistence.*;

import java.beans.ConstructorProperties;

@Entity

public class ClientEmail {

    @EmbeddedId
    private final Pk id;
    private final Boolean isMain;

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private ClientEmail() {
        this(null, null, null);
    }

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

        /**
         * Don't use. Requires by JPA.
         */
        @Deprecated
        private Pk() {
            this(null, null);
        }

        private Pk(final Client client, final Email email) {
            this.client = client;
            this.email = email;
        }
    }
}
