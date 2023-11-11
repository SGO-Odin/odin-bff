package com.odin.odinbff.authentcation;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AutenticacaoRequest {
    @NotBlank
    @Email
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public AutenticacaoRequest(@NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken paraAuthenticationToken() {
        return new UsernamePasswordAuthenticationToken(this.login, this.senha);
    }
}
