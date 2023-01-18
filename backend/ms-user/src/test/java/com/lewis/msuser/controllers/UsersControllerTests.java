package com.lewis.msuser.controllers;
import com.lewis.msuser.MsUserApplication;
import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.mockbeans.ConfigBeans;
import com.lewis.msuser.services.contracts.UserService;
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
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Profile("test")
@SpringBootTest(classes = {MsUserApplication.class}, properties = {"spring.jpa.defer-datasource-initialization=false"})
@Sql(scripts = {"classpath:create-data-test.sql"},executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:delete-data-test.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@Import(value = {ConfigBeans.class})
@AutoConfigureMockMvc
@Transactional
public class UsersControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvert userConvert;

    @Test
    @DisplayName("getAll Return UsersDTO")
    public void getAllReturnUsersDTO() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users")
                        .param("pagNumber","0").param("pagSize","2").param("sortBy","username")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded",hasSize(2)));
    }

    @Test
    @DisplayName("getAll throws IllegalArgumentException")
    public void getAllThrowsIllegalArgumentException() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users")
                .param("pagNumber","1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message",is("Invalid Input please verify!")));
    }

    @Test
    @DisplayName("getById returns UserDTO")
    public void getByIdReturnsUserDTO() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/{id}","82f3e5f0-83be-4a8e-88db-fc124230f022")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username",is("Lewis")));
    }

    @Test
    @DisplayName("GetById throws Exception")
    public void getByIdThrowsException() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/{id}","72f3e5f0-83be-4a8e-88db-fc124230f022")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
