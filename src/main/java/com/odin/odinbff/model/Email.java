package com.odin.odinbff.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private final String email;


    /**
     * Don't use. Requires by JPA.
     */
    @Deprecated
    private Email() {
        this(null);
    }


    public Email(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
