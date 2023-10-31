package com.odin.odinbff.model;


import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private final String email;

    public Email(String email) {
        this.email = email;
    }


    public String getEmail() {
        return email;
    }
}
