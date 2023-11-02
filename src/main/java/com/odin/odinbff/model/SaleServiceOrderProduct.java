package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class SaleServiceOrderProduct {
    @Id
    @ManyToOne(optional = false)
    @JoinColumns({@JoinColumn(name = "serviceOrder"), @JoinColumn(name = "product")})
    private final ServiceOrderProduct serviceOrderProduct;

    @Id
    @ManyToOne(optional = false)
    @JoinColumns({@JoinColumn(name = "sale"), @JoinColumn(name = "product")})
    private final SaleProduct saleProduct;

    /**
     *
     * @param serviceOrderProduct
     * @param saleProduct
     */
    public SaleServiceOrderProduct(ServiceOrderProduct serviceOrderProduct, SaleProduct saleProduct) {
        if(serviceOrderProduct.product != saleProduct.getProduct())
            throw new IllegalArgumentException("product in sale-product and service_order-product must be the same!");

        this.serviceOrderProduct = serviceOrderProduct;
        this.saleProduct = saleProduct;
    }

    public ServiceOrderProduct getServiceOrderProduct() {
        return serviceOrderProduct;
    }

    public SaleProduct getSaleProduct() {
        return saleProduct;
    }
}