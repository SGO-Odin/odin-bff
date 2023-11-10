package com.odin.odinbff.controller.address.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.address.request.DistrictFormRequest;
import com.odin.odinbff.model.address.District;
import com.odin.odinbff.model.address.PublicPlace;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.DistrictRepository;
import com.odin.odinbff.repository.PublicPlaceRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public final class PublicPlaceFormRequest {

    @JsonProperty
    @NotBlank
    private final String name;

    @JsonProperty
    private final Long existingDistrictId;

    @JsonProperty
    @Valid
    private final DistrictFormRequest district;

    @JsonProperty
    @NotNull
    private final PublicPlace.Types type;

    @JsonCreator
    public PublicPlaceFormRequest(final String name,
                                  final Long existingDistrictId,
                                  final PublicPlace.Types type,
                                  final DistrictFormRequest district) {
        this.name = name.trim();
        this.existingDistrictId = existingDistrictId;
        this.type = type;
        this.district = district;
    }

    public PublicPlace toModel(final PublicPlaceRepository publicPlaceRepository,
                                final DistrictRepository districtRepository,
                               final CityRepository cityRepository,
                               final StateRepository stateRepository) {


        if(existingDistrictId == null) {
            final District district = this.district.toModel(districtRepository, cityRepository, stateRepository);

            if(district.getId() != null) {
                final Optional<PublicPlace> possiblePublicPlace = publicPlaceRepository.findByNameAndDistrict(name, district);
                if(possiblePublicPlace.isPresent()) {
                    return possiblePublicPlace.get();
                }
            }

            return new PublicPlace(name, null,
                    type,
                    district);
        } else {
            return new PublicPlace(name, null,
                    type,
                    districtRepository.getReferenceById(existingDistrictId));
        }
    }
}
