package com.odin.odinbff.controller.address;

import com.odin.odinbff.model.address.State;

public final class StateResponse {
    private final State state;

    public StateResponse(final State state) {
        this.state = state;
    }

    public Long getId() {
        return state.getId();
    }

    public String getName() {
        return state.getName();
    }

    public String getAcronym() {
        return state.getAcronym();
    }

    public Boolean isFederalDistrict() {
        return state.isFederalDistrict();
    }
}
