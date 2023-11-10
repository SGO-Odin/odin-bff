package com.odin.odinbff.controller.serviceorder;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;
import com.odin.odinbff.repository.ProductRepository;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public final class  ServiceOrderProductFormRequest {
    @NotNull
    @JsonProperty
    private final Long productId;

    @Positive
    @JsonProperty
    private final Short quantity;

    @JsonCreator
    public ServiceOrderProductFormRequest(Long productId, Short quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    Product toModel(final ProductRepository productRepository) {
        return productRepository.getReferenceById(productId);
    }

    public Short getQuantity() {
        return quantity;
    }
}
