package com.odin.odinbff.controller.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Email;

public final class EmailFormRequest {

    @JsonProperty
    @jakarta.validation.constraints.Email
    private final String email;

    @JsonProperty
    private final Boolean isMain;

    @JsonCreator
    public EmailFormRequest(final String email, final Boolean isMain) {
        this.email = email.trim();
        this.isMain = isMain != null && isMain;
    }

    public Email toEmail() {
        return new Email(email);
    }

    public Boolean getIsMain() {
        return isMain;
    }
}
