package com.odin.odinbff.controller.serviceorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

public final class ServiceOrderFormRequest {

    @JsonProperty
    @NotNull
    private final Long client;
    @JsonProperty
    private final BigDecimal discountValue;
    @JsonProperty
    private final BigDecimal additionalValue;

    @NotEmpty
    @JsonProperty
    private final Set<@Valid ServiceOrderProductFormRequest> products;
    @JsonProperty
    @NotNull
    private final PrescriptionFormRequest prescription;

    public ServiceOrderFormRequest(final Long client,
                                   final BigDecimal discountValue,
                                   final BigDecimal additionalValue,
                                   final Set<ServiceOrderProductFormRequest> products,
                                   final PrescriptionFormRequest prescription) {
        this.client = client;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.products = products;
        this.prescription = prescription;
    }

    public ServiceOrder toModel(final ClientRepository clientRepository,
                                final ProductRepository productRepository) {
        final Optional<Client> possibleClient = clientRepository.findById(client);

        if(possibleClient.isEmpty()) {
            // todo: validations to not valid client id
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found Client with id = " + client);
        }

        final ServiceOrder serviceOrder =  new ServiceOrder(possibleClient.get(),
                discountValue,
                additionalValue,
                prescription.toModel(possibleClient.get()));

       products.forEach(product -> {
           serviceOrder.addProduct(product.toModel(productRepository), product.getQuantity());
       });

        return serviceOrder;
    }
}
