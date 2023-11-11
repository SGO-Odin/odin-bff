package com.odin.odinbff.controller.serviceorder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.payment.PaymentFormRequest;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    @Min(1)
    @Max(Long.MAX_VALUE)
    private final Long number;

    @JsonProperty
    private final BigDecimal discountValue;
    @JsonProperty
    private final BigDecimal additionalValue;

    @NotEmpty
    @JsonProperty
    private final Set<@Valid ServiceOrderProductFormRequest> products;

    @NotNull
    @Valid
    @JsonProperty
    private final PrescriptionFormRequest prescription;

    @JsonProperty
    private final Set<@Valid PaymentFormRequest> payments;

    @JsonCreator
    public ServiceOrderFormRequest(final Long client,
                                   final Long number,
                                   final BigDecimal discountValue,
                                   final BigDecimal additionalValue,
                                   final Set<ServiceOrderProductFormRequest> products,
                                   final PrescriptionFormRequest prescription,
                                   final Set<PaymentFormRequest> payments) {
        this.client = client;
        this.number = number;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.products = products;
        this.prescription = prescription;
        this.payments = payments;
    }

    public ServiceOrder toModel(final ClientRepository clientRepository,
                                final ProductRepository productRepository) {
        final Optional<Client> possibleClient = clientRepository.findById(client);

        if(possibleClient.isEmpty()) {
            // todo: validations to not valid client id
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found Client with id = " + client);
        }

        final ServiceOrder serviceOrder =  new ServiceOrder(possibleClient.get(),
                number,
                discountValue,
                additionalValue,
                prescription.toModel(possibleClient.get()));

       products.forEach(soProduct -> {
           soProduct.addToModel(productRepository, serviceOrder);
       });

       if(payments != null) {
           for (var payment : payments) {
               payment.addToModel(serviceOrder);
           }
       }

        return serviceOrder;
    }
}
