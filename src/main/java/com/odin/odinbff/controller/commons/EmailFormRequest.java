package com.odin.odinbff.controller.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Email;

public final class EmailFormRequest {

    @JsonProperty
    private final String email;

    @JsonProperty
    private final Boolean isMain;

    @JsonCreator
    public EmailFormRequest(final String email, final Boolean isMain) {
        this.email = email;
        this.isMain = isMain;
    }

    public Email toEmail() {
        return new Email(email);
    }

    public Boolean getIsMain() {
        return isMain;
    }
}
