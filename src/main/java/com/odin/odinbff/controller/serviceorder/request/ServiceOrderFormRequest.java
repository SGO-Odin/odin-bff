package com.odin.odinbff.controller.serviceorder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.payment.PaymentFormRequest;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.prescription.Prescription;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
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

    @Nullable
    @JsonProperty
    private final Long prescriptionId;

    @Valid
    @Nullable
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
                                   @Nullable final Long prescriptionId,
                                   @Nullable final PrescriptionFormRequest prescription,
                                   final Set<PaymentFormRequest> payments) throws BindException {
        this.client = client;
        this.number = number;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.products = products;

        if(prescriptionId == null && prescription == null) {
            var b = new BindException(this, "prescription");
            b.addError(new ObjectError("prescription", "prescriptionId ou prescription is required!"));
        }

        this.prescriptionId = prescriptionId;
        this.prescription = prescription;
        this.payments = payments;
    }

    public ServiceOrder toModel(final ClientRepository clientRepository,
                                final ProductRepository productRepository,
                                final EntityManager entityManager) {
        final Optional<Client> possibleClient = clientRepository.findById(client);

        if(possibleClient.isEmpty()) {
            // todo: validations to not valid client id
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Not found Client with id = " + client);
        }

        Prescription prescription;

        if(prescriptionId == null) {
            prescription = this.prescription.toModel(possibleClient.get());
        } else {
            prescription = entityManager.getReference(Prescription.class, prescriptionId);
        }

        final ServiceOrder serviceOrder =  new ServiceOrder(possibleClient.get(),
                number,
                discountValue,
                additionalValue,
               prescription);

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
