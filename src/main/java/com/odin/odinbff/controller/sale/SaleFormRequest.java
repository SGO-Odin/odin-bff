package com.odin.odinbff.controller.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.repository.ClientRepository;
import com.odin.odinbff.repository.ProductRepository;
import com.odin.odinbff.repository.ServiceOrderRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Optional;
import java.util.Set;

public final class SaleFormRequest {

    @NotNull
    @JsonProperty
    private final Long clientId;

    @Positive
    @JsonProperty
    private final Long serviceOrderId;


    @NotEmpty
    @JsonProperty
    private final Set<@Valid SaleProductFormRequest> saleProducts;

    @NotEmpty
    @JsonProperty
    private final Set<@Valid SalePaymentFormRequest> salePayments;

    @JsonCreator
    public SaleFormRequest(final Long clientId,
                           final Long serviceOrderId,
                           final Set<SaleProductFormRequest> saleProducts,
                           final Set<@Valid SalePaymentFormRequest> salePayments) {
        this.clientId = clientId;
        this.serviceOrderId = serviceOrderId;
        this.saleProducts = saleProducts;
        this.salePayments = salePayments;
    }

    public Sale toModel(final ClientRepository clientRepository,
                        final ServiceOrderRepository serviceOrderRepository,
                        final ProductRepository productRepository) {

        final var client = clientRepository.getReferenceById(clientId);

        final var sale = new Sale(
                client,
                Optional.of(serviceOrderId).flatMap(serviceOrderRepository::findById)
                .orElse(null));

        // todo: retrieve all ids in a single query (create repository methods) ->>

        for(var saleProduct: saleProducts) {
            saleProduct.addToModel(productRepository, sale);
        }

        for (SalePaymentFormRequest salePayment : salePayments) {
            salePayment.addToModel(sale);
        }

        return sale;
    }
}
