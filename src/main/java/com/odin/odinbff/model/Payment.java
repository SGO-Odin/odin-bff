package com.odin.odinbff.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public final class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;

    @NotNull
    @Positive
    @Column(nullable = false)
    private final Byte quantityInstallments;

    @Enumerated(EnumType.STRING)
    private final Type type;

    @Column(nullable = false)
    @Positive
    private final BigDecimal amount;

    @ManyToOne(optional = false)
    private final Sale sale;

    @ManyToOne
    private final ServiceOrder serviceOrder;

    private Payment(final Long id, final Type type, final BigDecimal amount, final Byte quantityInstallments, final Sale sale, final ServiceOrder serviceOrder) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.quantityInstallments = quantityInstallments;
        this.sale = sale;
        this.serviceOrder = serviceOrder;
    }

    public Payment(final Type type, final BigDecimal amount, final Byte quantityInstallments, final Sale sale) {
        this(null, type, amount, quantityInstallments, sale, null);
    }

    public Payment(final Type type, final BigDecimal amount, final Sale sale) {
        this(type, amount, (byte) 1, sale);
    }

    public Byte getQuantityInstallments() {
        return quantityInstallments;
    }

    public Sale getSale() {
        return sale;
    }

    public Long getId() {
        return id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
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
}
