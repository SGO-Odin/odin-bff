package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class PublicPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    private final String name;
    @ManyToOne(optional = false)
    private final District district;
    @Enumerated(EnumType.STRING)
    private final Types type;


    public PublicPlace(Long id, String name, Types type, District district) {
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
