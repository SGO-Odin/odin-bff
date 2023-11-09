package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.District;

public final class DistrictFormRequest {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final CityFormRequest city;

    @JsonCreator
    public DistrictFormRequest(final String name, final CityFormRequest city) {
        this.name = name;
        this.city = city;
    }

    public District toModel() {
        return new District(name, city.toModel());
    }
}
