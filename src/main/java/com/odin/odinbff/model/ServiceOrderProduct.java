package com.odin.odinbff.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class ServiceOrderProduct {
    @Id
    @ManyToOne
    protected ServiceOrder serviceOrder;

    @Id
    @ManyToOne
    protected Product product;

    @Column(nullable = false)
    protected Short quantity;

    @Column(nullable = false)
    protected BigDecimal salePrice;

    public ServiceOrderProduct(ServiceOrder serviceOrder, Product product, Short quantity, BigDecimal salePrice) {

        this.serviceOrder = serviceOrder;
        this.product = product;
        this.quantity = quantity;
        this.salePrice = salePrice;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public Product getProduct() {
        return product;
    }

    public Short getQuantity() {
        return quantity;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public BigDecimal calculateFinalPriceValue () {
        return salePrice.multiply(BigDecimal.valueOf(quantity));
    }

}
