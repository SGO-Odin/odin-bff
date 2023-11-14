package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.DiscountAndAdditionalPriceValue;
import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import com.odin.odinbff.model.serviceorder.ServiceOrderProduct;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Entity
public class Sale extends HistoryLoggable<Sale> implements DiscountAndAdditionalPriceValue, Payable, HasLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Nullable
    @OneToOne(cascade = CascadeType.ALL)
    private final ServiceOrder importedServiceOrder;

    @ManyToOne(optional = false)
    private final Client client;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private final Set<SaleProduct> saleProducts = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final Set<Payment> payments = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Transient
    private final Map<Long, ServiceOrderProduct> importedSOIds = new HashMap<>();

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Sale() {
        this(null, null, null);
    }

    private Sale (final Long id, final Client client, @Nullable final ServiceOrder importedServiceOrder) {
        this.id = id;
        this.client = client;
        this.importedServiceOrder = importedServiceOrder;

        if(importedServiceOrder != null) {
            for(var payment: importedServiceOrder.payments()) {
                addPayment(payment);
            }
            for(var soProduct: importedServiceOrder.getProducts()) {
                importedSOIds.put(soProduct.getProduct().getId(), soProduct);
            }
        }
    }

    public Sale (final Client client, @Nullable final ServiceOrder importedServiceOrder) {
        this(null, client, importedServiceOrder);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Nullable
    public ServiceOrder getImportedServiceOrder() {
        return importedServiceOrder;
    }

    public Client getClient() {
        return client;
    }

    public Set<SaleProduct> getSaleProducts() {
        return Collections.unmodifiableSet(saleProducts);
    }

    public void addSaleProduct(Product product, short quantity) {
        if(importedServiceOrder != null) {
            if(importedSOIds.containsKey(product.getId())) {
                var soProduct = importedSOIds.get(product.getId());
                saleProducts.add(new SaleProduct(
                        this,
                        product,
                        quantity,
                        quantity != soProduct.getQuantity() ? product.getCurrentSalePrice() : soProduct.getSalePrice()
                ));
            }
        }
    }

    public void addPayment(final Payment payment) {
        payment.setSale(this);
        payments.add(payment);
    }

    public BigDecimal calculateFinalPriceValue () {
        return saleProducts
                .stream()
                .map(SaleProduct::calculateTotalValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Set<Payment> getPayments() {
        return Collections.unmodifiableSet(payments);
    }

    @PrePersist
    private void prePersist() {
        this.createdOn = LocalDateTime.now();
    }

}