package com.odin.odinbff.controller;

import com.odin.odinbff.controller.address.*;
import com.odin.odinbff.controller.brand.BrandResponse;
import com.odin.odinbff.controller.client.ClientFormRequest;
import com.odin.odinbff.controller.client.ClientResponse;
import com.odin.odinbff.controller.commons.PhoneFormRequest;
import com.odin.odinbff.model.Brand;
import com.odin.odinbff.model.PublicPlace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
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
                "00000000000",
                "0000000000",
                new AddressFormRequest(new PublicPlaceFormRequest("cityName",
                                PublicPlace.Types.STREET.name(),
                                new DistrictFormRequest("districtName",
                                        new CityFormRequest("cityName",
                                                new StateFormRequest("stateName",
                                                        "SN",
                                                        false),
                                                "00000000"))),
                        344,
                        "00000000",
                        "",
                        "" ),
                Set.of("email1@email.com", "email2@email.com"),
                Set.of(new PhoneFormRequest("33", "923456781", false)));

        MockHttpServletResponse result = mvc.perform(post(Api.CLIENT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(clientRequestJson.write(clientFormRequest).getJson())
                        )
                .andReturn()
                .getResponse();
        System.out.println(result.getContentAsString());

        assertThat(result.getStatus()).isEqualTo(HttpStatus.OK.value());
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
