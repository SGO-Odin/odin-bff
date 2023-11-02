package com.odin.odinbff.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class ServiceOrder implements DiscountAndAdditionalPriceValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @ManyToOne(optional = false)
    private final Client client;
    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal discountValue;
    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal additionalValue;
    @ManyToOne(optional = false)
    private final Prescription prescription;
    @Enumerated(EnumType.STRING)
    private final StatusType status;
    private final LocalDateTime openedOn;
    private final LocalDateTime closedOn;
    private final LocalDateTime canceledOn;
    @Column(nullable = false)
    private final LocalDateTime createdOn;
    @Column(nullable = false)
    private final LocalDateTime updatedOn;

    @OneToMany(mappedBy = "serviceOrder")
    private final Set<ServiceOrderProduct> products = new HashSet<>();

    private ServiceOrder(final Long id,
                         final Client client,
                         final BigDecimal discountValue,
                         final BigDecimal additionalValue,
                         final Prescription prescription,
                         final StatusType status,
                         final LocalDateTime openedOn,
                         final LocalDateTime closedOn,
                         final LocalDateTime canceledOn,
                         final LocalDateTime createdOn,
                         LocalDateTime updatedOn) {
        this.id = id;
        this.client = client;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.prescription = prescription;
        this.status = status;
        this.openedOn = openedOn;
        this.closedOn = closedOn;
        this.canceledOn = canceledOn;
        this.createdOn = createdOn;
        this.updatedOn = updatedOn;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Set<Product> getProducts() {
        return products
                .stream()
                .map(ServiceOrderProduct::getProduct)
                .collect(Collectors.toUnmodifiableSet());
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public BigDecimal getAdditionalValue() {
        return additionalValue;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public StatusType getStatus() {
        return status;
    }

    public LocalDateTime getOpenedOn() {
        return openedOn;
    }

    public LocalDateTime getClosedOn() {
        return closedOn;
    }

    public LocalDateTime getCanceledOn() {
        return canceledOn;
    }

    public BigDecimal calculateFinalPriceValue() throws IllegalStateException {
        var totalSalePrice = products.stream()
                .map(ServiceOrderProduct::calculateFinalPriceValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (totalSalePrice.compareTo(discountValue) < 0)
            throw new IllegalStateException("Valor do desconto maior do que do que o valor total da ordem de serviço!");

        return totalSalePrice
                .add(additionalValue)
                .subtract(discountValue);
    }

    public void addProduct(final Product product, final Short quantity, final BigDecimal salePrice) {
        products.add(new ServiceOrderProduct(this, product, quantity, salePrice));
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public enum StatusType {
        OPENED, CLOSED, CANCELED
    }
}