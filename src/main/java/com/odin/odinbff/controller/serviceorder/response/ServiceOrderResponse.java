package com.odin.odinbff.controller.serviceorder.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odin.odinbff.controller.product.ProductResponse;
import com.odin.odinbff.controller.serviceorder.response.PrescriptionResponse;
import com.odin.odinbff.model.serviceorder.ServiceOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public final class ServiceOrderResponse {

    private final ServiceOrder serviceOrder;

    public ServiceOrderResponse(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    public Long getId() {
        return serviceOrder.getId();
    }

    public Long getClient() {
        return serviceOrder.getClient().getId();
    }

    public Set<ProductResponse> getProducts() {
        return serviceOrder.getProducts()
                .stream()
                .map(sop -> new ProductResponse(sop.getProduct()))
                .collect(Collectors.toUnmodifiableSet());
    }

    public BigDecimal getDiscountValue() {
        return serviceOrder.getDiscountValue();
    }

    public BigDecimal getAdditionalValue() {
        return serviceOrder.getAdditionalValue();
    }

    public PrescriptionResponse getPrescription() {
        return new PrescriptionResponse(serviceOrder.getPrescription());
    }

    public ServiceOrder.StatusType getStatus() {
        return serviceOrder.getStatus();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH::mm:ss")

    public LocalDateTime getClosedOn() {
        return serviceOrder.getClosedOn();
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH::mm:ss")
    public LocalDateTime getCanceledOn() {
        return serviceOrder.getCanceledOn();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm:ss")
    public LocalDateTime getCreatedOn() {
        return serviceOrder.getCreatedOn();
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH::mm:ss")

    public LocalDateTime getUpdatedOn() {
        return serviceOrder.getUpdatedOn();
    }
}
