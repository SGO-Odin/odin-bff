package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.PublicPlace;

public final class PublicPlaceFormRequest {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final DistrictFormRequest district;
    @JsonProperty
    private final String type;

    @JsonCreator
    public PublicPlaceFormRequest(String name, String type, DistrictFormRequest district) {
        this.name = name;
        this.type = type;
        this.district = district;
    }

    public PublicPlace toModel() {
        return new PublicPlace(name, PublicPlace.Types.valueOf(type), district.toModel());
    }
}
