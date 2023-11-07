package com.odin.odinbff.controller;


import com.odin.odinbff.controller.brand.BrandFormRequest;
import com.odin.odinbff.controller.brand.BrandResponse;
import com.odin.odinbff.model.Brand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class BrandControllerTest extends BaseControllerTest {


    @Autowired private
    JacksonTester<BrandFormRequest> brandRequestJson;
    @Autowired
    private JacksonTester<BrandResponse> brandResponseJson;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void when_post_a_valid_request_save_with_success() throws Exception {
        MockHttpServletResponse result = mvc.perform(post(Api.Brand.BRAND_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(brandRequestJson.write(new BrandFormRequest("Brand Success")).getJson()))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        final String expectedLocation = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path(Api.Brand.BRAND_READ_BY_ID)
                                .buildAndExpand(1L)
                                        .toUriString();

        assertThat(result.getRedirectedUrl()).isEqualTo(expectedLocation);
    }

    @Test
    void when_post_a_invalid_request_return_bad_request() throws Exception {
        MockHttpServletResponse result = mvc.perform(post(Api.Brand.BRAND_RESOURCE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(""))
                .andReturn()
                .getResponse();

        assertThat(result.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

     record BrandResponse(Long id, String name){}
}
