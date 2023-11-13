package com.odin.odinbff.controller.authentication;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public final class LoginFormRequest {

    @NotBlank
    @Length(min = User.Constants.VALIDATION_NAME_MIN_LENGTH, max = User.Constants.VALIDATION_NAME_MAX_LENGTH)
    @JsonProperty
    private final String userName;

    @NotBlank
    @Length(min = User.Constants.VALIDATION_PASSWORD_MIN_LENGTH, max = User.Constants.VALIDATION_PASSWORD_MAX_LENGTH)
    @Pattern(regexp = User.Constants.VALIDATION_PASSWORD_PATTERN)
    @JsonProperty
    private final String password;


    @JsonCreator
    public LoginFormRequest(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }


    public UsernamePasswordAuthenticationToken converte() {
        return new UsernamePasswordAuthenticationToken(userName, password);
    }
}
