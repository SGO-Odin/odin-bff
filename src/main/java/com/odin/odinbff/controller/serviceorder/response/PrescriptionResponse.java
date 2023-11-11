package com.odin.odinbff.controller.serviceorder.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odin.odinbff.controller.serviceorder.response.VisionProblemResponse;
import com.odin.odinbff.model.prescription.Prescription;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public final class PrescriptionResponse {

    private final Prescription prescription;

    public PrescriptionResponse(final Prescription prescription) {
        this.prescription = prescription;
    }

    public Long getId() {
        return prescription.getId();
    }

    public LocalDate getExpirationDate() {
        return prescription.getExpirationDate();
    }

    public Float getAdditional() {
        return prescription.getAdditional();
    }

    public Long getClient() {
        return prescription.getClient().getId();
    }

    public Set<VisionProblemResponse> getVisionProblems() {
        return prescription.getProblems().stream().map(VisionProblemResponse::new).collect(Collectors.toUnmodifiableSet());
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public LocalDateTime getCreatedOn() {
        return prescription.getCreatedOn();
    }
}
