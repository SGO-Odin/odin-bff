package com.odin.odinbff.model.address;

import jakarta.persistence.*;

@Entity
public final class State {
    @Id
    private final Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private final StateAcronyms acronym;
    @Column(nullable = false, unique = true)
    private final String name;
    @Column(nullable = false)
    private final Boolean isFederalDistrict;

    @Column(nullable = false)
    private final String region;

    private State(final Long id,
                  final StateAcronyms acronym,
                  final String name,
                  final Boolean isFederalDistrict,
                  final String region) {
        this.id = id;
        this.name = name;
        this.acronym = acronym;
        this.isFederalDistrict = isFederalDistrict;
        this.region = region;
    }

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private State() {
        this(null, null, null, null, null);
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public StateAcronyms getAcronym() {
        return acronym;
    }

    public Boolean isFederalDistrict() {
        return isFederalDistrict;
    }

    public String getRegion() {
        return region;
    }

    public enum StateAcronyms {
        AC, AM, PA, AP, TO, MA, PI,
        CE, RN, PB, PE, AL, SE, BA,
        MG, ES, RJ, SP, PR, SC, RS,
        MS, MT, GO, DF, RO, RR, NONE

    }
}