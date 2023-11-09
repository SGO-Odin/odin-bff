package com.odin.odinbff.controller;

import com.odin.odinbff.controller.address.*;
import com.odin.odinbff.controller.commons.EmailFormRequest;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.controller.purveyor.PurveyorFormRequest;
import com.odin.odinbff.controller.purveyor.PurveyorResponse;
import com.odin.odinbff.model.address.PublicPlace;
import com.odin.odinbff.model.address.State;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

final class PurveyorControllerTest extends BaseControllerTest {

    private final static String URI_PATH = Api.Purveyor.PURVEYOR_RESOURCE;

    @Autowired
    private JacksonTester<PurveyorFormRequest> purveyorFormRequestJson;
    @Autowired
    private JacksonTester<PurveyorResponse> purveyorResponseJson;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void when_post_a_valid_request_save_with_success() throws Exception {
        final PurveyorFormRequest purveyorFormRequest = new PurveyorFormRequest("FirstName",
                "LastName",
                false,
                new AddressFormRequest("00000000",
                        new PublicPlaceFormRequest("cityName",
                                null, PublicPlace.Types.STREET.name(),
                        new DistrictFormRequest("districtName",
                                null, new CityFormRequest("cityName",
                                        State.StateAcronyms.AL))),
                        344,
                        "00000000",
                        "",
                        "" ),
                Set.of(new PhoneFormRequest("33", "923456781", false)),
                Set.of(
                        new EmailFormRequest("email1@email.com", false),
                        new EmailFormRequest("email2@email.com", false)));

        MockHttpServletResponse result = mvc.perform(post(Api.Client.CLIENT_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purveyorFormRequestJson.write(purveyorFormRequest).getJson())
                )
                .andReturn()
                .getResponse();

        String expectedLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(Api.Purveyor.PURVEYOR_READ_BY_ID)
                .buildAndExpand(1).toUriString();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(result.getRedirectedUrl()).isEqualTo(expectedLocation);

    }

}
