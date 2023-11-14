package com.odin.odinbff.controller.serviceorder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.prescription.Prescription;
import com.odin.odinbff.model.prescription.VisionProblem;
import jakarta.validation.constraints.NotNull;

public final class VisionProblemFormRequest {
    @JsonProperty
    @NotNull
    private final VisionProblem.Types type;

    @JsonProperty
    @NotNull
    private final VisionProblem.PositionOfEyesType positionOfEyes;
    @JsonProperty
    @NotNull
    private final Float spherical;
    @NotNull
    @JsonProperty
    private final Float cylinder;
    @NotNull
    @JsonProperty
    private final Float axis;
    @NotNull
    @JsonProperty
    private final Float npd;
    @NotNull
    @JsonProperty
    private final Float height;

    @JsonCreator
    public VisionProblemFormRequest(final VisionProblem.Types type,
                                    final VisionProblem.PositionOfEyesType positionOfEyes,
                                    final Float spherical,
                                    final Float cylinder,
                                    final Float axis,
                                    final Float npd,
                                    final Float height) {
        this.type = type;
        this.positionOfEyes = positionOfEyes;
        this.spherical = spherical;
        this.cylinder = cylinder;
        this.axis = axis;
        this.npd = npd;
        this.height = height;
    }

    public void addToModel(final Prescription prescription) {
        prescription.addVisionProblem(type, positionOfEyes, spherical, cylinder, axis, npd, height);
    }
}
