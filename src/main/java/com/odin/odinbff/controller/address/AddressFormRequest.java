package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.Address;
import com.odin.odinbff.model.address.PublicPlace;
import com.odin.odinbff.model.address.ZipCode;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.DistrictRepository;
import com.odin.odinbff.repository.PublicPlaceRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.constraints.Pattern;

public final class AddressFormRequest {


    @Pattern(regexp = "^\\d{5}-?\\d{3}$")
    @JsonProperty
    private final String existingZipCode;

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
    public AddressFormRequest(final String existingZipCode,
                              final PublicPlaceFormRequest publicPlace,
                              final Integer number,
                              final String genericZipCode,
                              final String reference,
                              final String complement) {
        this.existingZipCode = existingZipCode;
        this.publicPlace = publicPlace;
        this.number = number;
        this.genericZipCode = genericZipCode;
        this.reference = reference;
        this.complement = complement;
    }

    public Address toModel(final PublicPlaceRepository publicPlaceRepository,
                           final DistrictRepository districtRepository,
                           final CityRepository cityRepository,
                           final StateRepository stateRepository) {
        PublicPlace publicPlace = null;

        if(existingZipCode == null) {
            publicPlace = this.publicPlace.toModel(publicPlaceRepository, districtRepository, cityRepository, stateRepository);
        } else {
            publicPlace = publicPlaceRepository.findByZipCode(new ZipCode(existingZipCode));
        }

        return new Address(publicPlace, number, new ZipCode(genericZipCode), reference, complement);
    }
}
