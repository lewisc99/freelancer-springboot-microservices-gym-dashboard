package com.lewis.msemployee.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.UUID;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.is;

@AutoConfigureMockMvc
@Transactional
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = {
        "classpath:data-create-test.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = {
        "classpath:data-delete-test.sql"})
@SpringBootTest(properties = {"spring.profiles.active=test"})
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

    @Autowired
    private Employee employee;
    @Autowired
    private Roles roles;

    public static final MediaType   APPLICATION_JSON_UTF8 =
            MediaType.APPLICATION_JSON;

    @BeforeAll
    public static void setup()
    {
        request = new MockHttpServletRequest();
    }

    @BeforeEach
    public void beforeEach()
    {


        employee.setId(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"));
        employee.setAge(20);
        employee.setUsername("Felipe");
        employee.setEmail("felipe@gmail.com");
        employee.setDoc("929393992");
        employee.setPassword("vida3788");

        roles.setId(UUID.fromString("6125011f-49fd-4cc8-a2d9-69e79ce127ab"));
        roles.setName("admin");
        employee.setRoles(Arrays.asList(roles));
    }



    @Test
    @DisplayName("GetById() Http request")
    public void getByIdHttpRequest() throws  Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/{id}", "b5517cd0-7d6a-42e2-a714-7a340f905e38"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", is("b5517cd0-7d6a-42e2-a714-7a340f905e38")))
                .andExpect(jsonPath("$.username", is("lewis")))
                .andExpect(jsonPath("$.email", is("lewis@example.com")))
                .andExpect(jsonPath("$.roles[0].name", is("admin")));


        assertNotNull(employeeDao.getById(UUID.fromString("b5517cd0-7d6a-42e2-a714-7a340f905e38")));
    }
    @Test
    @DisplayName("getById() not found Id Return error 404")
    public void getByIdNotFoundIdReturnError404() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees/{id}","33304dc3-564e-45b3-b91b-905aa76b74c4"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.status",is(404)))
                .andExpect(jsonPath("$.error",is("Employee Not Found")));

        assertNull(employeeDao.getById(UUID.fromString("43304dc3-564e-45b3-b91b-905aa76b74c4")));
    }

    @Test
    @DisplayName("create a valid Employee Http Request")
    public void createAValidEmployeeHttpRequest() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/employees")
                        .contentType(APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("create Invalid Employee Http Request")
    public void createInvalidEmployeeHttpRequest() throws Exception
    {
        employee.setEmail("");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/employees")
                .contentType(APPLICATION_JSON_UTF8).content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("create Invalid Employee validation Error Password cannot be null")
    public void createInvalidEmployeeValidationErrorPasswordCannotBeNull() throws Exception
    {
        employee.setPassword("");

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/employees")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors",hasSize(2)));
    }

}
