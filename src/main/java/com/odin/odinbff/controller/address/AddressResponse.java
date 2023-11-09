package com.odin.odinbff.controller.address;

import com.odin.odinbff.model.address.Address;

public final class AddressResponse {
    private final Address address;

    public AddressResponse(final Address address) {
        this.address = address;
    }

    public PublicPlaceResponse getPublicPlace() {
        return new PublicPlaceResponse(address.getPublicPlace());
    }

    public Integer getNumber() {
        return address.getNumber();
    }

    public String getGenericZipCode() {
        return address.getGenericZipCode().getNumber();
    }

    public String getReference() {
        return address.getReference();
    }

    public String getComplement() {
        return address.getComplement();
    }
}
