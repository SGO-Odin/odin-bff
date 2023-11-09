package com.odin.odinbff.controller.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.address.AddressFormRequest;
import com.odin.odinbff.controller.address.PublicPlaceResponse;
import com.odin.odinbff.controller.commons.EmailFormRequest;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.Cpf;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.DistrictRepository;
import com.odin.odinbff.repository.PublicPlaceRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
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

    @JsonProperty
    private final AddressFormRequest address;

    @JsonProperty
    @Valid
    private final Set<@Email String> emails;

    @NotEmpty
    @JsonProperty
    @Valid
    private final Set<PhoneFormRequest> phones;

    @JsonCreator
    public ClientFormRequest(final String firstName,
                             final String lastName,
                             final String cpf,
                             final String rg,
                             final AddressFormRequest address,
                             final Set<String> emails,
                             final Set<PhoneFormRequest> phones) {
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.cpf = cpf;
        this.rg = rg.trim();
        this.address = address;
        this.emails = emails;
        this.phones = phones;
    }

    public Client toModel(final PublicPlaceRepository publicPlaceRepository,
                          final DistrictRepository districtRepository,
                          final CityRepository cityRepository,
                          final StateRepository stateRepository) {
        Client client = new Client(firstName,
                lastName,
                new Cpf(cpf),
                rg, address.toModel(publicPlaceRepository, districtRepository, cityRepository, stateRepository));

        // TODO: validations to already exists emails and phones

        if (emails != null) emails.forEach(email -> { client.addEmail(new com.odin.odinbff.model.Email(email), false); });
        phones.forEach(phoneRequest -> {
            client.addPhone(phoneRequest.toModel(), phoneRequest.getIsMain());
        });

        return client;
    }
}
