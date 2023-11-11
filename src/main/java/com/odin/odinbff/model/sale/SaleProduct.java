package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.CalculableTotal;
import com.odin.odinbff.model.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;

@Entity
public final class SaleProduct implements CalculableTotal {
    @Id
    @ManyToOne(optional = false)
    private final Sale sale;

    @Id
    @ManyToOne(optional = false)
    private final Product product;
    private final BigDecimal salePrice;
    private final Short quantity;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private SaleProduct() {
        this(null, null, null, null);
    }

    private SaleProduct(Sale sale, Product product, BigDecimal salePrice, Short quantity) {
        this.sale = sale;
        this.product = product;
        this.salePrice = salePrice;
        this.quantity = quantity;
    }

    public SaleProduct(Sale sale, Product product, Short quantity) {
        this.sale = sale;
        this.product = product;
        this.salePrice = product.getCurrentSalePrice();
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

