package com.odin.odinbff.controller.payment;

import com.odin.odinbff.model.sale.Payment;
import com.odin.odinbff.model.sale.Sale;
import com.odin.odinbff.model.serviceorder.ServiceOrder;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class PaymentResponse {
    private final Payment payment;

    public PaymentResponse(Payment payment) {
        this.payment = payment;
    }

    public Byte getQuantityInstallments() {
        return payment.getQuantityInstallments();
    }

    public Long getSale() {
        return payment.getSale() == null ? null : payment.getSale().getId();
    }

    public Long getId() {
        return payment.getId();
    }


    public Long getServiceOrder() {
        return payment.getServiceOrder() == null? null : payment.getServiceOrder().getId();
    }

    public Payment.Type getType() {
        return payment.getType();
    }

    public BigDecimal getAmount() {
        return payment.getAmount();
    }

    public Boolean isInstallments() {
        return payment.isInstallments();
    }
}

