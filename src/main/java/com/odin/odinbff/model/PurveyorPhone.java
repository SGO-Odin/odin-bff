package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class PurveyorPhone {
    @EmbeddedId
    private final Pk id;

    public PurveyorPhone(final Purveyor purveyor, final Phone phone) {
        id = new Pk(purveyor, phone);
    }

    public Purveyor getPurveyor() {
        return id.purveyor;
    }

    public Phone getPhone() {
        return id.phone;
    }

    @Embeddable
    private static class Pk {
        @ManyToOne(optional = false)
        private final Purveyor purveyor;

        @Embedded
        private final Phone phone;

        private Pk(final Purveyor purveyor, final Phone phone) {
            this.purveyor = purveyor;
            this.phone = phone;
        }
    }

}