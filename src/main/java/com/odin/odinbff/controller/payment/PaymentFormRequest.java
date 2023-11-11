package com.odin.odinbff.controller.payment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.sale.Payable;
import com.odin.odinbff.model.sale.Payment;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public class PaymentFormRequest {

    @JsonProperty
    @NotNull
    private final Payment.Type type;

    @Positive
    @NotNull
    @JsonProperty
    @DecimalMin("1.0")
    private final BigDecimal amount;

    @Positive
    private final Byte quantityInstallments;

    protected PaymentFormRequest(final Payment.Type type, final BigDecimal amount, final Byte quantityInstallments) {
        this.type = type;
        this.amount = amount;
        this.quantityInstallments = quantityInstallments == null ? 1 : quantityInstallments;
    }

    public void addToModel(Payable payable) {
        final var payment = new Payment(null, type, amount, quantityInstallments);
        payable.addPayment(payment);
    }
}
