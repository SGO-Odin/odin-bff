package com.odin.odinbff.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest()
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
abstract class BaseControllerTest {

    @Autowired
    protected MockMvc mvc;



}
