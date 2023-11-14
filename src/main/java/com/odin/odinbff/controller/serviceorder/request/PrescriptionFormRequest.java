package com.odin.odinbff.controller.serviceorder.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.client.Client;
import com.odin.odinbff.model.prescription.Prescription;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Set;

public final class PrescriptionFormRequest {

    @JsonFormat( pattern = "yyyy-MM-dd")
    @NotNull
    @JsonProperty
    private final LocalDate expirationDate;
    @JsonProperty
    @NotNull
    private final Float additional;
    @JsonProperty
    @NotEmpty
    private final Set<@Valid VisionProblemFormRequest> visionProblems;

    @JsonCreator
    public PrescriptionFormRequest(final LocalDate expirationDate,
                                   final Float additional,
                                   final Set<VisionProblemFormRequest> visionProblems) {
        this.expirationDate = expirationDate;
        this.additional = additional;
        this.visionProblems = visionProblems;
    }

    public Prescription toModel(final Client client) {
        final Prescription prescription = new Prescription(expirationDate, additional, client);

        for(var visionProblemReq: visionProblems) {
            visionProblemReq.addToModel(prescription);
        }

        return prescription;
    }
}
