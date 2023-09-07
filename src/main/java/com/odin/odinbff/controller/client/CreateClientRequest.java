package com.odin.odinbff.controller.client;

import com.odin.odinbff.data.Client;
import com.odin.odinbff.data.Cpf;
import jakarta.validation.constraints.NotBlank;

public class CreateClientRequest {

    @NotBlank
    private String firstName;

    @NotBlank
    private String familyName;

    @NotBlank
    private String cpf;


    public Client toClient() {

        final var cpf = new Cpf(this.cpf);

        return new Client(this.firstName, this.familyName, cpf);

    }

}
