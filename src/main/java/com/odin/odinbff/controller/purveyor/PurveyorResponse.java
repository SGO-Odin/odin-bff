package com.odin.odinbff.controller.purveyor;

import com.odin.odinbff.controller.address.response.AddressResponse;
import com.odin.odinbff.controller.commons.EmailResponse;
import com.odin.odinbff.controller.commons.PhoneResponse;
import com.odin.odinbff.model.purveyor.Purveyor;

import java.util.Set;
import java.util.stream.Collectors;

public class PurveyorResponse {
    private final Purveyor purveyor;

    public PurveyorResponse(Purveyor purveyor) {
        this.purveyor = purveyor;
    }

    public Long getId() { return purveyor.getId(); }

    public String getTradingName() {
        return purveyor.getTradingName();
    }

    public String getCompanyName() {
        return purveyor.getCompanyName();
    }

    public Boolean isLaboratory() {
        return purveyor.isLaboratory();
    }

    public AddressResponse getAddress() {
        return new AddressResponse(purveyor.getAddress());
    }

    public Set<PhoneResponse> getPhones() {
        return purveyor.getPhones().stream().map(
                purveyorPhone -> new PhoneResponse(purveyorPhone.getPhone(), purveyorPhone.isMain())
        ).collect(Collectors.toUnmodifiableSet());
    }

    public Set<EmailResponse> getEmails() {
        return purveyor.getEmails().stream().map(
                purveyorEmail -> new EmailResponse(purveyorEmail.getEmail(), purveyorEmail.isMain())
        ).collect(Collectors.toUnmodifiableSet());
    }

}
