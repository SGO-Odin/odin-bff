package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class PurveyorEmail {

    @EmbeddedId
    private final Pk id;

    public PurveyorEmail(final Purveyor purveyor, Email email) {
        this.id = new Pk(purveyor, email);
    }

    public Purveyor getPurveyor() {
        return id.purveyor;
    }

    public Email getEmail() {
        return id.email;
    }

    @Embeddable
    private static class Pk {
        @ManyToOne(optional = false)
        private final Purveyor purveyor;

        @Embedded
        private final Email email;

        private Pk(final Purveyor purveyor, final Email email) {
            this.purveyor = purveyor;
            this.email = email;
        }
    }
}
