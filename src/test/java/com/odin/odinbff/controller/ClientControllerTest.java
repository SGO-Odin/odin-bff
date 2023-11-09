package com.odin.odinbff.controller;

import com.odin.odinbff.controller.address.*;
import com.odin.odinbff.controller.client.ClientFormRequest;
import com.odin.odinbff.controller.client.ClientResponse;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
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


class ClientControllerTest extends BaseControllerTest {
    @Autowired
    private JacksonTester<ClientFormRequest> clientRequestJson;
    @Autowired
    private JacksonTester<ClientResponse> clientResponseJson;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void when_post_a_valid_request_save_with_success() throws Exception {
        final ClientFormRequest clientFormRequest = new ClientFormRequest("FirstName",
                "LastName",
                "35742819005",
                "0000000000",
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
                Set.of( "email1@email.com",  "email2@email.com"),
                Set.of(new PhoneFormRequest("33", "923456781", false)));

        MockHttpServletResponse result = mvc.perform(post(Api.Client.CLIENT_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientRequestJson.write(clientFormRequest).getJson())
                        )
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        String expectedLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                .path(Api.Client.CLIENT_READ_BY_ID)
                .buildAndExpand(1).toUriString();

        assertThat(result.getRedirectedUrl()).isEqualTo(expectedLocation);

    }

    @Test
    void when_post_a_invalid_request_return_bad_request() throws Exception {
        MockHttpServletResponse result = mvc.perform(post("/api/brand")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }
}
