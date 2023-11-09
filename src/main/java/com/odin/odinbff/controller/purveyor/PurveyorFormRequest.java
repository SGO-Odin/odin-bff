package com.odin.odinbff.controller.purveyor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.address.AddressFormRequest;
import com.odin.odinbff.controller.commons.EmailFormRequest;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.model.purveyor.Purveyor;

import java.util.Set;

public final class PurveyorFormRequest {
    @JsonProperty
    private final String tradingName;
    @JsonProperty
    private final String companyName;
    @JsonProperty
    private final Boolean isLaboratory;
    @JsonProperty
    private final AddressFormRequest address;

    @JsonProperty
    private final Set<PhoneFormRequest> phones;

    @JsonProperty
    private final Set<EmailFormRequest> emails;

    @JsonCreator
    public PurveyorFormRequest(String tradingName, String companyName, Boolean isLaboratory, AddressFormRequest address, Set<PhoneFormRequest> phones, Set<EmailFormRequest> emails) {
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.isLaboratory = isLaboratory;
        this.address = address;
        this.phones = phones;
        this.emails = emails;
    }

    public Purveyor toModel() {

        Purveyor purveyor = new Purveyor(tradingName, companyName, isLaboratory, address.toModel());

        phones.forEach(purveyorPhone -> purveyor.addPhone(purveyorPhone.toModel(), purveyorPhone.getIsMain()));

        emails.forEach(purveyorEmail -> purveyor.addEmail(purveyorEmail.toEmail(), purveyorEmail.getIsMain()));

        return purveyor;
    }
}
