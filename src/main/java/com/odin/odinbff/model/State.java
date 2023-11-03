package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    private final String acronym;
    private final Boolean isFederalDistrict;

    private State(Long id, String name, String acronym, Boolean isFederalDistrict) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.isFederalDistrict = isFederalDistrict;
    }

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private State() {
        this(null, null, null, null);
    }

    public State(String name, String acronym, Boolean isFederalDistrict) {
        this(null, name, acronym, isFederalDistrict);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAcronym() {
        return acronym;
    }

    public Boolean getFederalDistrict() {
        return isFederalDistrict;
    }
}