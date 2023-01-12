package com.lewis.msemployee.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.EmployeeModel;
import com.lewis.msemployee.mockclasses.classesBeanConfig;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.EmployeeServiceImpl;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
@Import(classesBeanConfig.class)
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
    private  Employee employee2;
    @Autowired
    private Roles roles;

    public static final MediaType   APPLICATION_JSON_UTF8 =
            MediaType.APPLICATION_JSON;

    private final List<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeesDto employeesDto;

    @BeforeAll
    public static void setup()
    {
        request = new MockHttpServletRequest();
    }

    @PersistenceContext
    private EntityManager entityManager;

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

        employee2.setId(UUID.fromString("4013346b-feb3-44c8-8e3d-234dc6235852"));
        employee2.setAge(23);
        employee2.setUsername("Gisele");
        employee2.setEmail("gisele@gmail.com");
        employee2.setDoc("055454888");
        employee2.setPassword("vida3788");

        roles.setId(UUID.fromString("6125011f-49fd-4cc8-a2d9-69e79ce127ab"));
        roles.setName("coach");
        employee2.setRoles(Arrays.asList(roles));
        employees.addAll(Arrays.asList(employee, employee2));

        employeesDto.addEmployees(employees,"");
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

    @Test
    @DisplayName("getAll HttpRequest")
    public void getAllHttpRequest() throws Exception
    {
        entityManager.persist(employee2);
        entityManager.flush();
        employee.setId(UUID.fromString("2013346b-feb3-44c8-8e3d-234dc6235852"));
        entityManager.persist(employee);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded", hasSize(3)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8));
    }

    @Test
    @DisplayName("getAll SortBy Username")
    public void getAllSortByUsername() throws Exception
    {
        entityManager.persist(employee);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees")
                .contentType(APPLICATION_JSON_UTF8).param("sortBy","username"))
                .andExpect(jsonPath("$._embedded[0].username", is("Felipe")));
    }

    @Test
    @DisplayName("getAll SortBy Age")
    public void getAllSortByAge() throws Exception
    {
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees")
                        .contentType(APPLICATION_JSON_UTF8).param("sortBy","age"))
                .andExpect(jsonPath("$._embedded[1].username", is("Gisele")));
    }

    @Test
    @DisplayName("getAll params pagNumber One pagSize Two return size two")
    public void getAllParamsPagNumberOnePagSizeTwo() throws Exception
    {
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees").param("pagSize", "2").param("pagNumber","1")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded", hasSize(2)));
    }
    @Test
    @DisplayName("getAll params pagNumber One pagSize Two return size three")
    public void getAllParamsPagNumberOnePagSizeOne() throws Exception
    {
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees").param("pagSize", "0").param("pagNumber","0")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded", hasSize(3)));
    }

    @Test
    @DisplayName("getAll params pagSize or PagNumber is Invalid Format")
    public void getAllParamsPagsizeInvalidFormat() throws Exception
    {
        entityManager.persist(employee);
        entityManager.persist(employee2);
        entityManager.flush();

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employees").param("pagSize", "2a").param("pagNumber","2d")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }


    @Test
    @DisplayName("getAll params pagNumber One pagSize Two return size ")
    public void getAllThrowsExceptionNotFound() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/employeess")
                        .contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isNotFound());
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
    @DisplayName("update return 204")
    public void updateReturn204() throws Exception
    {
        entityManager.persist(employee);
        entityManager.flush();

        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("coach");

        EmployeeModel employeeModel = new EmployeeModel(employee.getUsername() + " Santos",employee.getAge(), employee.getDoc(),employee.getEmail(), roles  );

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/employees/{id}", "3413346b-feb3-44c8-8e3d-234dc6235852")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(employeeModel)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    @DisplayName("update return 404")
    public void updateReturn404() throws Exception
    {
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("coach");

        EmployeeModel employeeModel = new EmployeeModel(employee.getUsername() + " Santos",employee.getAge(), employee.getDoc(),employee.getEmail(), roles  );

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/employees/{id}", "3413346b-feb3-44c8-8e3d-234dc6235852")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(employeeModel)))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @DisplayName("update Employee throw a validation error ")
    public void updateThrowAValidationErrorBadRequest() throws Exception
    {
        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("coach");

        EmployeeModel employeeModel = new EmployeeModel("",employee.getAge(), employee.getDoc(),employee.getEmail(), roles  );
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/employees/{id}", "3413346b-feb3-44c8-8e3d-234dc6235852")
                .contentType(APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(employeeModel)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]", is("username cannot be Empty")));
    }

    @Test
    @DisplayName("update Employee return two Role")
    public void updateEmployeeReturnTwoRoles() throws Exception
    {
        entityManager.persist(employee);
        entityManager.flush();

        List<String> roles = new ArrayList<String>();
        roles.add("admin");
        roles.add("coach");

        EmployeeModel employeeModel = new EmployeeModel(employee.getUsername(),employee.getAge(), employee.getDoc(),employee.getEmail(), roles  );

        mockMvc.perform(MockMvcRequestBuilders.put("/v1/employees/{id}","3413346b-feb3-44c8-8e3d-234dc6235852")
                .content(objectMapper.writeValueAsString(employeeModel)).contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().is2xxSuccessful());

        Employee result = employeeService.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"));

        assertEquals(2, result.getRoles().size());
    }


}
