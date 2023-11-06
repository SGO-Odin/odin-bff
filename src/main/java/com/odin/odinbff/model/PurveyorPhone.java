package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class PurveyorPhone {
    @EmbeddedId
    private final Pk id;

    private final Boolean isMain;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private PurveyorPhone() {
        this(null, null, null);
    }

    public PurveyorPhone(final Purveyor purveyor, final Phone phone, final Boolean isMain) {
        id = new Pk(purveyor, phone);
        this.isMain = isMain;
    }

    public Purveyor getPurveyor() {
        return id.purveyor;
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
        private final Purveyor purveyor;

        @Embedded
        private final Phone phone;

        /**
         * Don't use. Don't remove. Requires by JPA.
         */
        @Deprecated
        private Pk() {
            this(null, null);
        }

        private Pk(final Purveyor purveyor, final Phone phone) {
            this.purveyor = purveyor;
            this.phone = phone;
        }
    }

}