package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.DiscountAndAdditionalPriceValue;
import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
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
public class Sale extends HistoryLoggable<Sale> implements DiscountAndAdditionalPriceValue, Payable, HasLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Null
    @OneToOne(cascade = CascadeType.ALL)
    private final ServiceOrder importedServiceOrder;

    @ManyToOne(optional = false)
    private final Client client;

    @OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private final Set<SaleProduct> saleProducts = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE)
    private final Set<Payment> payments = new HashSet<>();

    @Column(nullable = false)
    private LocalDateTime createdOn;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private Sale() {
        this(null, null, null);
    }

    private Sale (final Long id, final Client client, final ServiceOrder importedServiceOrder) {
        this.id = id;
        this.client = client;
        this.importedServiceOrder = importedServiceOrder;
    }

    public Sale (Client client, ServiceOrder importedServiceOrder) {
        this(null, client, importedServiceOrder);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public boolean hasImportedServiceOrder() {
        return importedServiceOrder != null;
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

    public Set<Payment> getPayments(){
        return Collections.unmodifiableSet(payments);
    }

    @PrePersist
    private void prePersist() {
        this.createdOn = LocalDateTime.now();
    }

}