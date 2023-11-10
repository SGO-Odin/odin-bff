package com.odin.odinbff.model.sale;

import com.odin.odinbff.model.HasLongId;
import com.odin.odinbff.model.audit.HistoryLoggable;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public final class Payment extends HistoryLoggable<Payment> implements HasLongId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    @Positive
    private final BigDecimal amount;

    @NotNull
    @Positive
    @Column(nullable = false)
    private final Byte quantityInstallments;

    @ManyToOne
    private Sale sale;

    @ManyToOne
    private ServiceOrder serviceOrder;

    public Payment(final Long id, final Type type, final BigDecimal amount, final Byte quantityInstallments) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.quantityInstallments = quantityInstallments;
    }

    public Byte getQuantityInstallments() {
        return quantityInstallments;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(@NotNull Sale sale) {
        if(sale == null)
            throw new IllegalArgumentException("sale não pode ser null!");
        this.sale = sale;
    }

    public Long getId() {
        return id;
    }


    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(@NotNull ServiceOrder serviceOrder) {
        if(serviceOrder == null)
            throw new IllegalArgumentException("serviceOrder não pode ser null!");
        this.serviceOrder = serviceOrder;
    }

    public Type getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal[] getInstallmentValues() {
        BigDecimal qttInstallments = BigDecimal.valueOf(quantityInstallments);
        BigDecimal result = amount.divide(qttInstallments, 2, RoundingMode.DOWN);

        BigDecimal[] values = new BigDecimal[quantityInstallments];
        for (byte i = 0; i<=quantityInstallments; i++) {
           values[i] = result;
        }

        values[1] = values[1].add(amount.subtract(result.multiply(qttInstallments)));

        return values;
    }

    public Boolean isInstallments() {
        return quantityInstallments > 1;
    }

    public enum Type {
        CREDIT_CARD, DEBIT_CARD, MONEY, PIX
    }

    private void checkState() {
        if(sale == null && serviceOrder == null)
            throw new IllegalStateException("Sales and Service Orders cannot both be null.");
    }

    @PrePersist
    private void prePersist() {
        checkState();
    }

    @PreUpdate
    private void preUpdate(){
        checkState();
    }
}
