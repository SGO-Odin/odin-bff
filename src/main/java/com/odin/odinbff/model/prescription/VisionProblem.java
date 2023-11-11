package com.odin.odinbff.model.prescription;

import com.odin.odinbff.model.audit.HistoryLoggable;
import jakarta.persistence.*;

@Entity
public final class VisionProblem extends HistoryLoggable<VisionProblem> {

    public enum PositionOfEyesType {
        RIGHT,
        LEFT
    }

    public enum Types {
        FAR,
        NEAR
    }

    @EmbeddedId
    private final Pk id;

    private final Float spherical;
    private final Float cylinder;
    private final Float axis;
    private final Float npd;
    private final Float height;

    /**
     * Don't use. Don't remove. Requires by JPA.
     */
    @Deprecated
    private VisionProblem() {
        this(null,
                null,
                null,
                null,
                null,
                null,
                null,
                null);
    }


    public VisionProblem(Prescription prescription,
                         Types type,
                         PositionOfEyesType positionOfEyes,
                         Float spherical,
                         Float cylinder,
                         Float axis,
                         Float npd,
                         Float height) {
        this.id = new Pk(prescription, type, positionOfEyes);
        this.spherical = spherical;
        this.cylinder = cylinder;
        this.axis = axis;
        this.npd = npd;
        this.height = height;
    }

    public Prescription getPrescription () {
        return id.prescription;
    }

    public Types getType () {
        return id.type;
    }

    public PositionOfEyesType getPositionOfEyes () {
        return id.positionOfEyes;
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

    @Embeddable
    private static final class Pk {
        @ManyToOne(optional = false)
        private final Prescription prescription;

        @Enumerated(EnumType.STRING)
        private final Types type;

        @Enumerated(EnumType.STRING)
        private final PositionOfEyesType positionOfEyes;

        private Pk(Prescription prescription, Types type, PositionOfEyesType positionOfEyes) {
            this.prescription = prescription;
            this.type = type;
            this.positionOfEyes = positionOfEyes;
        }

        /**
         * Don't use. Don't remove. Requires by JPA.
         */
        @Deprecated
        private Pk() {
            this(null, null, null);
        }
    }
}