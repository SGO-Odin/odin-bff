package com.odin.odinbff.controller.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.model.sale.SaleProduct;
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

    @JsonCreator
    public SaleFormRequest(final Long clientId, final Long serviceOrderId, final Set<SaleProductFormRequest> saleProducts) {
        this.clientId = clientId;
        this.serviceOrderId = serviceOrderId;
        this.saleProducts = saleProducts;
    }

    public Sale toModel(final ClientRepository clientRepository,
                        final ServiceOrderRepository serviceOrderRepository,
                        final ProductRepository productRepository) {
        final var client = clientRepository.getReferenceById(clientId);
        final var serviceOrder = serviceOrderRepository.findById(serviceOrderId);
        final var sale = new Sale(client, serviceOrder.orElse(null));
        for(var saleProduct: saleProducts) {
            saleProduct.addToModel(productRepository, sale);
        }
        return sale;
    }
}
