package com.odin.odinbff.controller.sale;

import com.odin.odinbff.controller.client.ClientResponse;
import com.odin.odinbff.controller.payment.PaymentResponse;
import com.odin.odinbff.model.sale.Sale;
import jakarta.annotation.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class SaleResponse {

    private final Sale sale;

    public SaleResponse(Sale sale) {
        this.sale = sale;
    }

    public Long getId() {
        return sale.getId();
    }


    public LocalDateTime getCreatedOn() {
        return sale.getCreatedOn();
    }

    @Nullable
    public Long getServiceOrderId() {
        return sale.getImportedServiceOrder() == null ? null : sale.getImportedServiceOrder().getId();
    }

    public ClientResponse getClientId() {
        return new ClientResponse(sale.getClient());
    }

    public Set<SaleProductResponse> getSaleProducts() {
        return sale.getSaleProducts().stream().map(SaleProductResponse::new)
                .collect(Collectors.toUnmodifiableSet());
    }

    public BigDecimal calculateFinalPriceValue () {
        return sale.calculateFinalPriceValue();
    }

    public Set<PaymentResponse> getSalePayments() {
        return sale.getPayments().stream().map(PaymentResponse::new).collect(Collectors.toUnmodifiableSet());
    }
}
