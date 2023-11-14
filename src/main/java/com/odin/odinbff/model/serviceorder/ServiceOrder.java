package com.odin.odinbff.model.serviceorder;

import com.odin.odinbff.model.DiscountAndAdditionalPriceValue;
import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.prescription.Prescription;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.product.Product;
import com.odin.odinbff.model.sale.Payable;
import com.odin.odinbff.model.sale.Payment;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = ServiceOrder.Constants.CONSTRAINT_UK_SERVICE_ORDER_NUMBER, columnNames = "number")
})
public final class ServiceOrder extends HistoryLoggable<ServiceOrder>
        implements DiscountAndAdditionalPriceValue, Payable, HasLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @ManyToOne(optional = false)
    private final Client client;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private  Long number;

    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal discountValue;
    @Column(nullable = false, precision = 16, scale = 2)
    private final BigDecimal additionalValue;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private final Prescription prescription;
    @Enumerated(EnumType.STRING)
    private StatusType status;
    private LocalDateTime closedOn;
    private final LocalDateTime canceledOn;
    @Column(nullable = false)
    private LocalDateTime createdOn;
    @Column(nullable = false)
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "serviceOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<ServiceOrderProduct> products = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<Payment> payments = new HashSet<>();

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private ServiceOrder() {
        this(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }

    public ServiceOrder(final Client client,
                        final Long number,
                        final BigDecimal discountValue,
                        final BigDecimal additionalValue,
                        final Prescription prescription){
        this(null,
                client,
                number,
                discountValue,
                additionalValue,
                prescription,
                StatusType.OPENED,
                null,
                null);
    }

    private ServiceOrder(final Long id,
                         final Client client,
                         final Long number, final BigDecimal discountValue,
                         final BigDecimal additionalValue,
                         final Prescription prescription,
                         final StatusType status,
                         final LocalDateTime closedOn,
                         final LocalDateTime canceledOn) {
        this.id = id;
        this.client = client;
        this.number = number;
        this.discountValue = discountValue;
        this.additionalValue = additionalValue;
        this.prescription = prescription;
        this.status = status;
        this.closedOn = closedOn;
        this.canceledOn = canceledOn;
    }

    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Long getNumber() {
        return number;
    }

    public Set<ServiceOrderProduct> getProducts() {
        return Collections.unmodifiableSet(products);
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
            throw new IllegalStateException("Valor do desconto maior do que o valor total da ordem de serviço!");

        return totalSalePrice
                .add(additionalValue)
                .subtract(discountValue);
    }

    public void addProduct(final Product product, final Short quantity) {
        products.add(new ServiceOrderProduct(this, product, quantity));
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public LocalDateTime getUpdatedOn() {
        return updatedOn;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void addPayment(final Payment payment) {
        payment.setServiceOrder(this);
        payments.add(payment);
    }

    public Set<Payment> payments() {
        return Collections.unmodifiableSet(payments);
    }

    public void close() {
        status = StatusType.CLOSED;
        closedOn = LocalDateTime.now();
    }

    @Override
    protected Set<String> attrToUpdateLog() {
        return Set.of();
    }

    public enum StatusType {
        OPENED, CLOSED, CANCELED
    }


    @PrePersist
    private void prePersist() {
        this.createdOn = LocalDateTime.now();
        this.updatedOn = LocalDateTime.of(createdOn.toLocalDate(), createdOn.toLocalTime());
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedOn = LocalDateTime.now();
    }

    public final static class Constants {
        public final static String CONSTRAINT_UK_SERVICE_ORDER_NUMBER = "uk_service_order_number";
    }
}