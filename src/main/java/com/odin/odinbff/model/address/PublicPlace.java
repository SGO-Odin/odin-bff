package com.odin.odinbff.model.address;

import jakarta.persistence.*;

@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(name = PublicPlace.CONSTRAINT_NAME_DISTRICT, columnNames = {"name", "district_id"})
})
public final class PublicPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @Column(nullable = false, length = PublicPlace.VALIDATION_NAME_LENGTH)
    private final String name;
    @Embedded
    @Column(unique = true)
    private final ZipCode zipCode;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "district_id", nullable = false)
    private final District district;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private final Types type;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private PublicPlace() {
        this(null, null, null, null, null);
    }

    public PublicPlace(final String name,
                       final ZipCode zipCode,
                       final Types type,
                       final District district) {
        this(null, name, zipCode, type, district);
    }

    private PublicPlace(Long id, String name, ZipCode zipCode, Types type, District district) {
        this.id = id;
        this.name = name;
        this.zipCode = zipCode;
        this.type = type;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public Types getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }

    public enum Types {
        AVENUE, PATHWAY, STREET, HIGHWAY, FARM, SQUARE, SETTLEMENT, PLATTER, ANOTHER
    }

    public final static String CONSTRAINT_NAME_DISTRICT = "uk_public_place_name_district";
    public final static byte VALIDATION_NAME_LENGTH = Byte.MAX_VALUE;
}
