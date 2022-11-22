package com.innova.ds.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.innova.ds.constant.ErrorMsgType;
import com.innova.ds.constant.ValidationType;
import com.innova.ds.dto.BaseInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ValidationControllerTest {

    @Autowired
    protected WebApplicationContext webApplicationContext;
    @Autowired
    protected MockMvc mvc;

    public static final String URI = "/api/verify/password";

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void verifyPasswordSuccess() throws Exception {
        String inputJson = mapToJson(new BaseInput("1wef23fdgbfd"));
        MvcResult mvcResult = getMvcResultWithParam(URI, inputJson);

        Map<String, String> mapResultExpected = new LinkedHashMap<>();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, mapToJson(mapResultExpected));
    }

    @Test
    public void verifyPasswordFail() throws Exception {
        String inputJson = mapToJson(new BaseInput("1123"));
        MvcResult mvcResult = getMvcResultWithParam(URI, inputJson);

        Map<String, String> mapResultExpected = new LinkedHashMap<>();
        mapResultExpected.put(ValidationType.LENGTH_RANGE.name(), ErrorMsgType.LENGTH_RANGE.getDescription());
        mapResultExpected.put(ValidationType.MIN_LOWERCASE.name(), ErrorMsgType.MIN_LOWERCASE.getDescription());
        mapResultExpected.put(ValidationType.NO_SEQUENCE.name(), ErrorMsgType.NO_SEQUENCE.getDescription());

        int status = mvcResult.getResponse().getStatus();
        assertEquals(500, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, mapToJson(mapResultExpected));
    }

    protected MvcResult getMvcResultWithParam(String uri, String inputJson) throws Exception {
        return mvc.perform(MockMvcRequestBuilders.post(uri)
                  .contentType(MediaType.APPLICATION_JSON_VALUE)
                  .content(inputJson)).andReturn();
    }

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }

}
