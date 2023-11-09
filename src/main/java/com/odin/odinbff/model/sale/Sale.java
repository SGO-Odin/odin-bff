package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.DiscountAndAdditionalPriceValue;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Sale implements DiscountAndAdditionalPriceValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Null
    @OneToOne
    private final ServiceOrder importedServiceOrder;

    @ManyToOne(optional = false)
    private final Client client;

    @OneToMany(mappedBy = "sale")
    private final Set<SaleProduct> saleProducts = new HashSet<>();

    @CreationTimestamp
    private final LocalDateTime createdOn;

    private Sale (final Long id, final Client client, final ServiceOrder importedServiceOrder, final LocalDateTime createdOn) {
        this.id = id;
        this.client = client;
        this.importedServiceOrder = importedServiceOrder;
        this.createdOn = createdOn;
    }

    public Sale (Client client, ServiceOrder importedServiceOrder) {
        this(null, client, importedServiceOrder, LocalDateTime.now());
    }

    public Sale (Client client) {
        this(client, null);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public ServiceOrder getImportedServiceOrder() {
        return importedServiceOrder;
    }

    public Client getClient() {
        return client;
    }

    public Set<SaleProduct> getSaleProducts() {
        return Collections.unmodifiableSet(saleProducts);
    }

    public void addSaleProduct(Product product, Short quantity) {
        saleProducts.add(new SaleProduct(this, product, quantity));
    }

    public BigDecimal calculateFinalPriceValue () {
        return saleProducts
                .stream()
                .map(SaleProduct::calculateTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}