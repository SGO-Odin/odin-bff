package com.odin.odinbff.model.serviceorder;

import com.odin.odinbff.model.product.Product;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;

@Entity
public class ServiceOrderProduct {
    @Id
    @ManyToOne
    private final ServiceOrder serviceOrder;

    @Id
    @ManyToOne
    private final Product product;

    @Column(nullable = false)
    private final Short quantity;

    @Column(nullable = false)
    private final BigDecimal salePrice;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private ServiceOrderProduct() {
        this(null, null, null);
    }


    public ServiceOrderProduct(ServiceOrder serviceOrder, Product product, Short quantity) {

        this.serviceOrder = serviceOrder;
        this.product = product;
        this.quantity = quantity;
        this.salePrice = product.getCurrentSalePrice();
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
