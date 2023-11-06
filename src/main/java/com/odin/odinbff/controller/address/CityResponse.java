package com.odin.odinbff.controller.address;

import com.odin.odinbff.model.City;
import com.odin.odinbff.model.State;
import com.odin.odinbff.model.ZipCode;

public final class CityResponse {
    private final City city;

    public CityResponse(final City city) {
        this.city = city;
    }

    public Long getId() {
        return city.getId();
    }

    public String getName() {
        return city.getName();
    }

    public StateResponse getState() {
        return new StateResponse(city.getState());
    }

    public ZipCode getGenericZipCode() {
        return city.getGenericZipCode();
    }
}
