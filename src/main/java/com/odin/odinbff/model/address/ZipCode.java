package com.odin.odinbff.model.address;

import jakarta.persistence.Embeddable;

@Embeddable
public class ZipCode {
    private final String zipCode;

    public ZipCode (String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private ZipCode() {
        this.zipCode = null;
    }

    public String getNumber() {
        return zipCode;
    }
}
