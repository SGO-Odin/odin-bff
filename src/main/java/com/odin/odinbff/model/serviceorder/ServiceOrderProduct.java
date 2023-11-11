package com.odin.odinbff.model.serviceorder;

import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.product.Product;
import jakarta.persistence.*;
import org.jetbrains.annotations.Contract;

import java.math.BigDecimal;
import java.util.Set;

@Entity
public final class ServiceOrderProduct extends HistoryLoggable<ServiceOrderProduct> {
    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final ServiceOrder serviceOrder;

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
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
        product = null;
        serviceOrder = null;
        quantity = null;
        salePrice = null;
    }


    public ServiceOrderProduct(final ServiceOrder serviceOrder, final Product product, final Short quantity) {
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

    @Override
    protected Set<String> attrToUpdateLog() {
        return Set.of();
    }
}
