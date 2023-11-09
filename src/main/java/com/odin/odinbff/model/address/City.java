package com.odin.odinbff.model.address;

import jakarta.persistence.*;

@Entity

public final class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Column(nullable = false, length = City.VALIDATION_NAME_LENGTH)
    private final String name;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final State state;

    @Embedded
    private ZipCode genericZipCode = null;

    @Column(nullable = false)
    private final Boolean isStandard;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private City() {
        this(null, null, null);
    }

    private City(final Long id, final String name, final State state) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.isStandard = false;
    }

    public City(final String name, final State state) {
        this(null, name, state);
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

    public Boolean isStandard() {
        return isStandard;
    }

    public void setGenericZipCode(ZipCode genericZipCode) {
        this.genericZipCode = genericZipCode;
    }

    public static final String CONSTRAINT_CITY_NAME_STATE = "uk_city_name_state";
    public static final byte VALIDATION_NAME_LENGTH = Byte.MAX_VALUE;
}