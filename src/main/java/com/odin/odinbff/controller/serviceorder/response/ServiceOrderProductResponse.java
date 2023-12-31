package com.odin.odinbff.controller.serviceorder.response;

import com.odin.odinbff.controller.product.ProductResponse;
import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;

import java.math.BigDecimal;

public final class ServiceOrderProductResponse {

    private final ServiceOrderProduct serviceOrderProduct;

    public ServiceOrderProductResponse(final ServiceOrderProduct serviceOrderProduct) {
        this.serviceOrderProduct = serviceOrderProduct;
    }

    public ProductResponse getProduct() {
        return new ProductResponse(serviceOrderProduct.getProduct());
    }

    public BigDecimal getSalePrice() {
        return serviceOrderProduct.getSalePrice();
    }

    public Short getQuantity() {
        return serviceOrderProduct.getQuantity();
    }
}
