package com.odin.odinbff.controller.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.sale.Payable;
import com.odin.odinbff.model.sale.Payment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public final class PaymentFormRequest {


    private final Long existingPaymentId;

    @JsonProperty
    @NotNull
    private final Payment.Type type;

    @Positive
    @NotNull
    @JsonProperty
    private final BigDecimal amount;

    @Positive
    private final Byte quantityInstallments;


    @JsonCreator
    public PaymentFormRequest(Long existingPaymentId, Payment.Type type, BigDecimal amount, Byte quantityInstallments) {
        this.existingPaymentId = existingPaymentId;
        this.type = type;
        this.amount = amount;
        this.quantityInstallments = quantityInstallments;
    }

    public void addToModel(Payable payable) {
        final var payment = new Payment(existingPaymentId, type, amount, quantityInstallments);
        payable.addPayment(payment);
    }
}
