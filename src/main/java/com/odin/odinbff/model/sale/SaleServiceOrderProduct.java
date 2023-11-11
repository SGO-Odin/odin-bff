package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;
import jakarta.persistence.*;

@Entity
public final class SaleServiceOrderProduct {
    @Id
    @ManyToOne(optional = false)
    @JoinColumns({@JoinColumn(name = "serviceOrder"), @JoinColumn(name = "product")})
    private final ServiceOrderProduct serviceOrderProduct;

    @Id
    @ManyToOne(optional = false)
    @JoinColumns({@JoinColumn(name = "sale"), @JoinColumn(name = "product")})
    private final SaleProduct saleProduct;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private SaleServiceOrderProduct(){
        saleProduct = null;
        serviceOrderProduct = null;
    }

    /**
     *
     * @param serviceOrderProduct
     * @param saleProduct
     */
    public SaleServiceOrderProduct(ServiceOrderProduct serviceOrderProduct, SaleProduct saleProduct) {
        if(serviceOrderProduct.getProduct() != saleProduct.getProduct())
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