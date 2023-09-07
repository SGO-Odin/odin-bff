package com.odin.odinbff.data;

import jakarta.persistence.Embeddable;

@Embeddable
public final class Cpf {
    private final String cpf;

    public Cpf(final String cpf) {
        this.cpf = cpf;
    }

    private boolean isValid() {
       // TODO: implement validation to cpf
        return true;
    }

    private String getFormatted() {
        // TODO: implement formatting to cpf
        return "";
    }
}
