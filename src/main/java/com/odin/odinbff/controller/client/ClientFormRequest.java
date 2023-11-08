package com.odin.odinbff.controller.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.odin.odinbff.controller.address.AddressFormRequest;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.model.Client;
import com.odin.odinbff.model.Cpf;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

public final class ClientFormRequest {

    @NotBlank
    @JsonProperty
    private final String firstName;

    @NotBlank
    @JsonProperty
    private final String lastName;

    @NotBlank
    @CPF
    @JsonProperty
    private final String cpf;

    @NotBlank
    @JsonProperty
    private final String rg;

    @NotNull
    @JsonProperty
    private final AddressFormRequest address;

    @JsonProperty
    private final Set<String> emails;

    @NotEmpty
    @JsonProperty  
    private final Set<PhoneFormRequest> phones;

    @JsonCreator
    public ClientFormRequest(final String firstName,
                             final String lastName,
                             final String cpf,
                             final String rg,
                             final AddressFormRequest address,
                             final Set<String> emails,
                             final Set<PhoneFormRequest> phones) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.rg = rg;
        this.address = address;
        this.emails = emails;
        this.phones = phones;
    }

    public Client toModel() {
        Client client = new Client(firstName,
                lastName,
                new Cpf(cpf),
                rg, address.toModel());

        // TODO: validations to already exists emails and phones

        emails.forEach(client::addEmail);
        phones.forEach(phoneRequest -> {
            client.addPhone(phoneRequest.toModel(), phoneRequest.getIsMain());
        });

        return client;
    }
}
