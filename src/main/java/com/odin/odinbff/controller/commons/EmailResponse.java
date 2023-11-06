package com.odin.odinbff.controller.commons;

import com.odin.odinbff.model.Email;

public final class EmailResponse {

    private final Email email;
    private final Boolean isMain;

    public EmailResponse(final Email email, final Boolean isMain) {

        this.email = email;
        this.isMain = isMain;
    }

    public String getEmail() {
        return email.getEmail();
    }

    public Boolean isMain() {
        return isMain;
    }
}
