package com.odin.odinbff.model.address;

import jakarta.persistence.*;

@Entity
public final class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final State state;

    @Embedded
    private final ZipCode genericZipCode;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private City() {
        this(null, null, null, null);
    }

    private City(final Long id, final String name, final State state, final ZipCode genericZipCode) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.genericZipCode = genericZipCode;
    }

    public City(final String name, final State state, final ZipCode genericZipCode) {
        this(null, name, state, genericZipCode);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public ZipCode getGenericZipCode() {
        return genericZipCode;
    }
}