package com.odin.odinbff.controller.product;

import com.odin.odinbff.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class ProductResponse {

    private final Product product;

    public ProductResponse(final Product product) {
        this.product = product;
    }

    public Long getId() {
        return product.getId();
    }

    public String getReference() {
        return product.getReference();
    }

    public String getName() {
        return product.getName();
    }

    public String getUnit() {
        return product.getUnit().name();
    }

    public Long getBrands() {
        return product.getBrands().getId();
    }

    public Long getPurveyor() {
        return product.getPurveyor().getId();
    }

    public Boolean isActive() {
        return product.isActive();
    }

    public Boolean isToStockControl() {
        return product.isInventoryControl();
    }

    public BigDecimal getPurchaseCost() {
        return product.getPurchaseCost();
    }

    public BigDecimal getCurrentSalePrice() {
        return product.getCurrentSalePrice();
    }

    public Short getCurrentStock() {
        return product.getCurrentStock();
    }

    public Short getMinStock() {
        return product.getMinStock();
    }

    public String getLocation() {
        return product.getLocation();
    }

    public LocalDateTime getCreatedOn() {
        return product.getCreatedOn();
    }

    public LocalDateTime getUpdatedOn() {
        return product.getUpdatedOn();
    }
}
