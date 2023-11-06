package com.odin.odinbff.controller.serviceorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.Api;
import com.odin.odinbff.model.Client;
import com.odin.odinbff.model.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Optional;

public class ServiceOrderFormRequest {

    @JsonProperty
    @NotNull
    private final Long client;
    @JsonProperty
    private final BigDecimal discountValue;
    @JsonProperty
    private final BigDecimal additionalValue;
    @JsonProperty
    @NotNull
    private final PrescriptionFormRequest prescription;

    public ServiceOrderFormRequest(final Long client,
                                   final BigDecimal discountValue,
                                   final BigDecimal additionalValue,
                                   final PrescriptionFormRequest prescription) {
        this.client = client;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.prescription = prescription;
    }

    public ServiceOrder toModel(final ClientRepository clientRepository) {
        final Optional<Client> possibleClient = clientRepository.findById(client);

        if(possibleClient.isEmpty()) {
            // todo: validations to not valid client id
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found Client with id = " + client);
        }

        return new ServiceOrder(possibleClient.get(), discountValue, additionalValue, prescription.toModel(possibleClient.get()));
    }
}
