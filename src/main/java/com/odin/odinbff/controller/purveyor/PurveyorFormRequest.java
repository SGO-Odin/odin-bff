package com.odin.odinbff.controller.purveyor;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.odin.odinbff.controller.address.request.AddressFormRequest;
import com.odin.odinbff.controller.commons.EmailFormRequest;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.model.purveyor.Purveyor;
import com.odin.odinbff.repository.CityRepository;
import com.odin.odinbff.repository.DistrictRepository;
import com.odin.odinbff.repository.PublicPlaceRepository;
import com.odin.odinbff.repository.StateRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public final class PurveyorFormRequest {
    @JsonProperty
    @NotBlank
    private final String tradingName;
    @JsonProperty
    @NotBlank
    private final String companyName;
    @JsonProperty
    @NotNull
    private final Boolean isLaboratory;
    @JsonProperty
    private final AddressFormRequest address;

    @JsonProperty
    @NotEmpty
    @Valid
    private final Set<PhoneFormRequest> phones;

    @JsonProperty
    @Valid
    private final Set<EmailFormRequest> emails;

    @JsonCreator
    public PurveyorFormRequest(final String tradingName,
                               final String companyName,
                               final Boolean isLaboratory,
                               final AddressFormRequest address,
                               final Set<PhoneFormRequest> phones,
                               final Set<EmailFormRequest> emails) {
        this.tradingName = tradingName.trim();
        this.companyName = companyName.trim();
        this.isLaboratory = isLaboratory;
        this.address = address;
        this.phones = phones;
        this.emails = emails;
    }

    public Purveyor toModel(final PublicPlaceRepository publicPlaceRepository,
                            final DistrictRepository districtRepository,
                            final CityRepository cityRepository,
                            final StateRepository stateRepository) {

        Purveyor purveyor = new Purveyor(tradingName,
                companyName,
                isLaboratory,
                address == null ? null : address.toModel(publicPlaceRepository, districtRepository, cityRepository, stateRepository));

        phones.forEach(purveyorPhone -> purveyor.addPhone(purveyorPhone.toModel(), purveyorPhone.getIsMain()));

        if(emails != null)
            emails.forEach(purveyorEmail -> purveyor.addEmail(purveyorEmail.toEmail(), purveyorEmail.getIsMain()));

        return purveyor;
    }
}
