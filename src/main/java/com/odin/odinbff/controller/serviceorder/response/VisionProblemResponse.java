package com.odin.odinbff.controller.serviceorder.response;

import com.odin.odinbff.model.prescription.VisionProblem;

public class VisionProblemResponse {

    private final VisionProblem visionProblem;

    public VisionProblemResponse(final VisionProblem visionProblem) {
        this.visionProblem = visionProblem;
    }

    public VisionProblem.Types getType () {
        return visionProblem.getType();
    }

    public VisionProblem.PositionOfEyesType getPositionOfEyes () {
        return visionProblem.getPositionOfEyes();
    }

    public Float getSpherical () {
        return visionProblem.getSpherical();
    }

    public Float getCylinder () {
        return visionProblem.getCylinder();
    }

    public Float getAxis () {
        return visionProblem.getAxis();
    }

    public Float getNpd () {
        return visionProblem.getNpd();
    }

    public Float getHeight () {
        return visionProblem.getHeight();
    }
}
