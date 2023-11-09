package com.odin.odinbff.model.address;

import jakarta.persistence.*;

@Entity
public class PublicPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final District district;
    @Enumerated(EnumType.STRING)
    private final Types type;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private PublicPlace() {
        this(null, null, null, null);
    }

    public PublicPlace(String name, Types type, District district) {
        this(null, name, type, district);
    }

    private PublicPlace(Long id, String name, Types type, District district) {
        this.id = id;
        this.name = name;
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
        AVENUE, PATHWAY, STREET, HIGHWAY, FARM
    }
}
