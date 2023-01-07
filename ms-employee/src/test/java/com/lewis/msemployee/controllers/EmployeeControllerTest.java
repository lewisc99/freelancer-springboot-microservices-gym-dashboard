package com.lewis.msemployee.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewis.msemployee.MsEmployeeApplication;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.transaction.Transactional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@SpringBootTest(classes= MsEmployeeApplication.class)
@AutoConfigureMockMvc
@Sql(scripts = {"classpath:data-create-test.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:data-delete-test.sql"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class EmployeeControllerTest {


    private  static MockHttpServletRequest request;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    ObjectMapper objectMapper;

    public static final MediaType   APPLICATION_JSON_UTF8 =
            MediaType.APPLICATION_JSON;


    @BeforeAll
    public static void setup()
    {
        request = new MockHttpServletRequest();
    }



    @Test
    public void getByIdHttpRequest() throws  Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/{id}", "13304dc3-564e-45b3-b91b-905aa76b74c4"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("13304dc3-564e-45b3-b91b-905aa76b74c4")))
                .andExpect(jsonPath("$.username", is("lewis")))
                .andExpect(jsonPath("$.email", is("lewis@example.com")))
                .andExpect(jsonPath("$.roles[0].name", is("admin")));


        assertNotNull(employeeDao.getById(UUID.fromString("13304dc3-564e-45b3-b91b-905aa76b74c4")));
    }
    @Test
    public void getByIdNotFoundIdReturnError404() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/{id}","43304dc3-564e-45b3-b91b-905aa76b74c4"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(404)))
                .andExpect(jsonPath("$.error",is("Employee Not Found")));

        assertNull(employeeDao.getById(UUID.fromString("43304dc3-564e-45b3-b91b-905aa76b74c4")));

    }

}
