package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.Address;
import com.odin.odinbff.model.address.ZipCode;

public final class AddressFormRequest {

    @JsonProperty
    private final PublicPlaceFormRequest publicPlace;
    @JsonProperty
    private final Integer number;
    @JsonProperty
    private final String genericZipCode;
    @JsonProperty
    private final String reference;
    @JsonProperty
    private final String complement;

    @JsonCreator
    public AddressFormRequest(PublicPlaceFormRequest publicPlace, Integer number, String genericZipCode, String reference, String complement) {
        this.publicPlace = publicPlace;
        this.number = number;
        this.genericZipCode = genericZipCode;
        this.reference = reference;
        this.complement = complement;
    }

    public Address toModel() {
        return new Address(publicPlace.toModel(), number, new ZipCode(genericZipCode), reference, complement);
    }
}
