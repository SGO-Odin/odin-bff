package com.odin.odinbff.model;

import jakarta.persistence.*;

@Entity
public class VisionProblem {

    public enum PositionOfEyesType {
        RIGHT,
        LEFT
    }

    public enum Types {
        FAR,
        NEAR
    }

    @OneToOne
    @Id
    private final Prescription prescription;

    @Id
    @Enumerated(EnumType.STRING)
    private final Types type;

    @Id
    @Enumerated(EnumType.STRING)
    private final PositionOfEyesType positionOfEyes;
    private final Float spherical;
    private final Float cylinder;
    private final Float axis;
    private final Float npd;
    private final Float height;

    public VisionProblem(Prescription prescription,
                         Types type,
                         PositionOfEyesType positionOfEyes,
                         Float spherical,
                         Float cylinder,
                         Float axis,
                         Float npd,
                         Float height) {
        this.prescription = prescription;
        this.type = type;
        this.positionOfEyes = positionOfEyes;
        this.spherical = spherical;
        this.cylinder = cylinder;
        this.axis = axis;
        this.npd = npd;
        this.height = height;
    }

    public Prescription getPrescription () {
        return prescription;
    }

    public Types getType () {
        return type;
    }

    public PositionOfEyesType getPositionOfEyes () {
        return positionOfEyes;
    }

    public Float getSpherical () {
        return spherical;
    }

    public Float getCylinder () {
        return cylinder;
    }

    public Float getAxis () {
        return axis;
    }

    public Float getNpd () {
        return npd;
    }

    public Float getHeight () {
        return height;
    }
}