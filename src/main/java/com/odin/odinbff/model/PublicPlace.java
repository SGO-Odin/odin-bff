package com.odin.odinbff.model;

import com.odin.odinbff.model.type.PublicPlaceType;
import jakarta.persistence.*;

@Entity
public class PublicPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Enumerated(EnumType.STRING)
    private PublicPlaceType type;
    private final String name;

    @ManyToOne(optional = false)
    private final District district;


    public PublicPlace(Long id, String name,  PublicPlaceType type, District district) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public PublicPlaceType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public District getDistrict() {
        return district;
    }


}
