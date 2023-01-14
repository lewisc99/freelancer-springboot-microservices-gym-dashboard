package com.lewis.msemployee.controllers;


import com.lewis.msemployee.mockclasses.classesBeanConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "classpath:data-create-test.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
        "classpath:data-delete-test.sql"})
@Import(classesBeanConfig.class)
@Transactional
@SpringBootTest(properties = {"spring.profiles.active="})
public class RolesControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 =
            MediaType.APPLICATION_JSON;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("findAll Return Roles")
    public void findAllReturnRoles() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/roles").contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(3)))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }


}
