package com.odin.odinbff.controller.address.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.City;
import com.odin.odinbff.model.address.District;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.DistrictRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public final class DistrictFormRequest {
    @NotBlank
    @JsonProperty
    private final String name;

    @JsonProperty
    private final Long existingCityId;

    @Valid
    @JsonProperty
    private final CityFormRequest city;


    @JsonCreator
    public DistrictFormRequest(final String name, final Long existingCityId, final CityFormRequest city) {
        this.name = name.trim();
        this.existingCityId = existingCityId;
        this.city = city;
    }

    public District toModel(final DistrictRepository districtRepository,
                            final CityRepository cityRepository,
                            final StateRepository stateRepository) {
        if(existingCityId == null) {
            final City city = this.city.toModel(cityRepository, stateRepository);

            if(city.getId() != null) {
                final Optional<District> possibleDistrict = districtRepository.findByCityAndNameIgnoreCase(city, name);

                if(possibleDistrict.isPresent()) {
                    return possibleDistrict.get();
                }
            }

            return new District(name, city);
        } else {
            return new District(name, cityRepository.getReferenceById(existingCityId));
        }
    }
}
