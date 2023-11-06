package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public final class PurveyorEmail {

    @EmbeddedId
    private final Pk id;

    private final Boolean isMain;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private PurveyorEmail() {
        this(null, null, null);
    }

    public PurveyorEmail(final Purveyor purveyor, Email email, Boolean isMain) {
        this.id = new Pk(purveyor, email);
        this.isMain = isMain;
    }

    public Purveyor getPurveyor() {
        return id.purveyor;
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
        private final Purveyor purveyor;

        @Embedded
        private final Email email;

        /**
         * Don't use. Don't remove. Requires by JPA.
         */
        @Deprecated
        private Pk() {
            this(null, null);
        }

        private Pk(final Purveyor purveyor, final Email email) {
            this.purveyor = purveyor;
            this.email = email;
        }
    }
}
