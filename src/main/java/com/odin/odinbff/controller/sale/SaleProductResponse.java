package com.odin.odinbff.controller.sale;

import com.odin.odinbff.controller.product.ProductResponse;
import com.odin.odinbff.model.sale.SaleProduct;

import java.math.BigDecimal;

public final class SaleProductResponse {
    private final SaleProduct saleProduct;


    public SaleProductResponse(SaleProduct saleProduct) {
        this.saleProduct = saleProduct;
    }

    public Long getSale() {
        return saleProduct.getSale().getId();
    }

    public ProductResponse getProduct() {
        return new ProductResponse(saleProduct.getProduct());
    }

    public BigDecimal getSalePrice() {
        return saleProduct.getSalePrice();
    }

    public Short getQuantity() {
        return saleProduct.getQuantity();
    }

    public BigDecimal calculateTotalValue() {
        return saleProduct.calculateTotalValue();
    }

}
