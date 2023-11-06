package com.odin.odinbff.controller.serviceorder;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Prescription;
import com.odin.odinbff.model.VisionProblem;
import jakarta.validation.constraints.NotNull;

public class VisionProblemFormRequest {
    @JsonProperty
    @NotNull
    private final VisionProblem.Types type;

    @JsonProperty
    @NotNull
    private final VisionProblem.PositionOfEyesType positionOfEyes;
    @JsonProperty
    @NotNull
    private final Float spherical;
    @JsonProperty
    private final Float cylinder;
    @JsonProperty
    private final Float axis;
    @JsonProperty
    private final Float npd;
    @JsonProperty
    private final Float height;

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
