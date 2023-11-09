package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.City;
import com.odin.odinbff.model.address.State;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Optional;

public final class CityFormRequest {
    @JsonProperty
    @NotBlank
    private final String name;
    @NotNull
    @JsonProperty
    private final State.StateAcronyms stateAcronym;

    @JsonCreator
    public CityFormRequest(final String name, final State.StateAcronyms stateAcronym) {
        this.name = name.trim();
        this.stateAcronym = stateAcronym;
    }

    public City toModel(final CityRepository cityRepository, final StateRepository stateRepository) {
        final State state = stateRepository.findByAcronym(stateAcronym);
        final Optional<City> possibleCity = cityRepository.findByStateAndName(state, name);
        return possibleCity.orElseGet(() -> new City(name, state));
    }
}
