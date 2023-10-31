package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @ManyToOne
    private final PublicPlace publicPlace;
    private final Integer number;

    @Embedded
    private final ZipCode genericZipCode;
    private final String reference;
    private final String complement;

    public Address(Long id, PublicPlace publicPlace, Integer number, ZipCode genericZipCode, String reference, String complement) {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.genericZipCode = genericZipCode;
        this.reference = reference;
        this.complement = complement;
    }

    public Long getId() {
        return id;
    }

    public PublicPlace getPublicPlace() {
        return publicPlace;
    }

    public Integer getNumber() {
        return number;
    }

    public ZipCode getGenericZipCode() {
        return genericZipCode;
    }

    public String getReference() {
        return reference;
    }

    public String getComplement() {
        return complement;
    }
}