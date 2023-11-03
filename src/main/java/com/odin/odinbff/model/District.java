package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final City city;

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private District() {
        this(null, null);
    }

    private District(final Long id, final String name, final City city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public District(final String name, final City city) {
        this(null, name, city);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public City getCity() {
        return city;
    }
}
