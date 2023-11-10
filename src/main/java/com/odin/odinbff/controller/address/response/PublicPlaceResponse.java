package com.odin.odinbff.controller.address.response;

import com.odin.odinbff.controller.address.response.DistrictResponse;
import com.odin.odinbff.model.address.PublicPlace;

public final class PublicPlaceResponse {
    private final PublicPlace publicPlace;

    public PublicPlaceResponse(final PublicPlace publicPlace) {

        this.publicPlace = publicPlace;
    }

    public Long getId() {
        return publicPlace.getId();
    }

    public String getType() {
        return publicPlace.getType().name();
    }

    public String getName() {
        return publicPlace.getName();
    }

    public DistrictResponse getDistrict() {
        return new DistrictResponse(publicPlace.getDistrict());
    }

}
