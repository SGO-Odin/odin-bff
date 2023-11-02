package com.odin.odinbff.controller.brand;

import com.odin.odinbff.model.Brand;

public final class BrandResponse {
    private final Brand brand;

    public BrandResponse(final Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return brand.getName();
    }

    public Long getId() {
        return brand.getId();
    }
}
