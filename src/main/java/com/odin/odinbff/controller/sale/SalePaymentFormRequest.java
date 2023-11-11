package com.odin.odinbff.controller.sale;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.payment.PaymentFormRequest;
import com.odin.odinbff.model.sale.Payable;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class SalePaymentFormRequest {

    @Nullable
    private final Long existingPaymentId;

    @JsonProperty
    @NotNull
    @Valid
    private final PaymentFormRequest payment;

    @JsonCreator
    protected SalePaymentFormRequest(@Nullable final Long existingPaymentId,
                                     final PaymentFormRequest payment) {
        this.existingPaymentId = existingPaymentId;
        this.payment = payment;
    }

    public void addToModel(Payable payable) {
        if (existingPaymentId == null) {
            payment.addToModel(payable);
        }
    }
}
