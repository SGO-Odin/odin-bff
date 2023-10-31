package com.odin.odinbff.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ZipCode {
    private final String zipCode;

    public ZipCode (String zipCode) {
        this.zipCode = zipCode;
    }

    public String getZipCode() {
        return zipCode;
    }
}
