package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class ClientPhone {

    @EmbeddedId
    private final Pk id;

    private final Boolean isMain;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private ClientPhone() {
        this(null, null, null);
    }

    public ClientPhone(final Client client, final Phone phone, final Boolean isMain) {
        this.id = new Pk(client, phone);
        this.isMain = isMain;
    }

    public Client getClient() {
        return id.client;
    }

    public Phone getPhone() {
        return id.phone;
    }

    public Boolean isMain() {
        return isMain;
    }

    @Embeddable
    private static class Pk {
        @ManyToOne(optional = false)
        private final Client client;
        @Embedded
        private final Phone phone;

        /**
         * Don't use. Don't remove. Requires by JPA.
         */
        @Deprecated
        private Pk() {
            this(null, null);
        }

        private Pk(Client client, Phone phone) {
            this.client = client;
            this.phone = phone;
        }
    }
}
