package com.odin.odinbff.model;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Embeddable
public class Email {

    @Column(nullable = false)
    private final String email;

    @Column(nullable = false)
    private final Boolean isMain;

    public Email(String email) {
        this(email, false);
    }

    public Email(String email, Boolean isMain) {
        this.email = email;
        this.isMain = isMain;
    }

    public String getEmail() {
        return email;
    }

    public Boolean isMain() {
        return this.isMain;
    }
}
