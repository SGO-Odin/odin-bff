package com.odin.odinbff.controller.brand;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.odin.odinbff.model.Brand;

import java.time.LocalDateTime;

public final class BrandResponse {
    private final Brand brand;

    public BrandResponse(final Brand brand) {
        this.brand = brand;
    }

    public Long getId() {
        return brand.getId();
    }

    public String getName() {
        return brand.getName();
    }

    public Boolean getIsActive() {
        return  brand.isActive();
    }

    public LocalDateTime getCreatedOn() {
        return brand.getCreatedOn();
    }

    public LocalDateTime getUpdatedOn() {
        return brand.getUpdatedOn();
    }

}
