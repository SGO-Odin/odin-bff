package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;

    @ManyToOne(optional = false)
    private final State state;

    @Embedded
    private final ZipCode genericZipCode;

    public City(Long id, String name, State state, final ZipCode genericZipCode) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.genericZipCode = genericZipCode;
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