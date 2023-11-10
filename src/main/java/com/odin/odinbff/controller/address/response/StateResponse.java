package com.odin.odinbff.controller.address.response;

import com.odin.odinbff.model.address.State;

public final class StateResponse {
    private final State state;

    public StateResponse(final State state) {
        this.state = state;
    }

    public String getName() {
        return state.getName();
    }

    public State.StateAcronyms getAcronym() {
        return state.getAcronym();
    }

    public Boolean isFederalDistrict() {
        return state.isFederalDistrict();
    }
}
