package com.odin.odinbff.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ZipCode {
    private final String zipCode;

    public ZipCode (String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private ZipCode() {
        this.zipCode = null;
    }

    public String getZipCode() {
        return zipCode;
    }
}
