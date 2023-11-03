package com.odin.odinbff.controller.commons;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Phone;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public final class PhoneFormRequest {
    @JsonProperty
    @NotBlank
    private final String ddd;
    @JsonProperty
    @NotBlank
    private final String number;

    @JsonProperty
    @NotNull
    private final Boolean isMain;

    @JsonCreator
    public PhoneFormRequest(final String ddd, final String number, final Boolean isMain) {
        this.ddd = ddd;
        this.number = number;
        this.isMain = isMain;
    }

    public Phone toModel() {
        // TODO: validation phone input
        return new Phone(ddd, number);
    }

    public Boolean isMain() {
        return isMain;
    }
}
