package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.City;
import com.odin.odinbff.model.address.ZipCode;

public final class CityFormRequest {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final StateFormRequest state;
    @JsonProperty
    private final String genericZipCode;

    @JsonCreator
    public CityFormRequest(String name, StateFormRequest state, String genericZipCode) {
        this.name = name;
        this.state = state;
        this.genericZipCode = genericZipCode;
    }

    public City toModel() {
        return new City(name, state.toModel(), new ZipCode(genericZipCode));
    }
}
