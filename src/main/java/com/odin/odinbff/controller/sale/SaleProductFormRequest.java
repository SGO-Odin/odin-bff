package com.odin.odinbff.controller.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public final class SaleProductFormRequest {

    @NotNull
    @JsonProperty
    private final Long productId;

    @NotNull
    @JsonProperty
    @Positive
    private final Short quantity;

    @JsonCreator
    public SaleProductFormRequest(Long productId, Short quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public void addToModel(final ProductRepository productRepository, final Sale sale) {
        final var product = productRepository.getReferenceById(productId);
        sale.addSaleProduct(product, quantity);
    }
}

