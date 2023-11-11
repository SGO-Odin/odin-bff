package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.CalculableTotal;
import com.odin.odinbff.model.product.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Entity
public final class SaleProduct implements CalculableTotal {
    @Id
    @ManyToOne(optional = false)
    private final Sale sale;

    @Id
    @ManyToOne(optional = false)
    private final Product product;
    @Column(nullable = false)
    @Positive
    private final BigDecimal salePrice;
    @NotNull
    @Positive
    @Column(nullable = false)
    private final Short quantity;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private SaleProduct() {
        this(null, null, null, null);
    }

    public SaleProduct(Sale sale, Product product, Short quantity, final BigDecimal salePrice) {
        this.sale = sale;
        this.product = product;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }

    public Sale getSale() {
        return sale;
    }

    public Product getProduct() {
        return product;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }

    public Short getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal calculateTotalValue() {
        return salePrice.multiply(BigDecimal.valueOf(quantity));
    }
}

