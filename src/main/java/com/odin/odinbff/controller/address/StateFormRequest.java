package com.odin.odinbff.controller.address;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.address.State;

public final class StateFormRequest {
    @JsonProperty
    private final String name;
    @JsonProperty
    private final String acronym;
    @JsonProperty
    private final Boolean isFederalDistrict;

    @JsonCreator
    public StateFormRequest(final String name, final String acronym, final Boolean isFederalDistrict) {
        this.name = name;
        this.acronym = acronym;
        this.isFederalDistrict = isFederalDistrict;
    }

    public State  toModel() {
        return new State(name, acronym, isFederalDistrict);
    }
}
