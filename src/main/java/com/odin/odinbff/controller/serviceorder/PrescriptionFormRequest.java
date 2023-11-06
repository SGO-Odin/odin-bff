package com.odin.odinbff.controller.serviceorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.model.Client;
import com.odin.odinbff.model.Prescription;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class PrescriptionFormRequest {

    @JsonFormat( pattern = "yyyy-mm-dd")
    @NotBlank
    @JsonProperty
    private final LocalDate expirationDate;
    @JsonProperty
    @NotNull
    private final Float additional;
    @JsonProperty
    private final Set<VisionProblemFormRequest> visionProblems = new HashSet<>();

    public PrescriptionFormRequest(final LocalDate expirationDate, final Float additional, final Set<VisionProblemFormRequest> visionProblems) {
        this.expirationDate = expirationDate;
        this.additional = additional;
    }

    public Prescription toModel(final Client client) {
        return new Prescription(expirationDate, additional, client);
    }
}
