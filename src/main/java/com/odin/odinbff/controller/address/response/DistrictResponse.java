package com.odin.odinbff.controller.address.response;

import com.odin.odinbff.controller.address.response.CityResponse;
import com.odin.odinbff.model.address.District;

public class DistrictResponse {
    private final District district;

    public DistrictResponse(final District district) {
        this.district = district;
    }

    public Long getId() {
        return district.getId();
    }

    public String getName() {
        return district.getName();
    }

    public CityResponse getCity() {
        return new CityResponse(district.getCity());
    }
}
