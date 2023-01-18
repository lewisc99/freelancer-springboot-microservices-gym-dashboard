package com.lewis.msuser.controllers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lewis.msuser.MsUserApplication;
import com.lewis.msuser.config.UserConvert;
import com.lewis.msuser.entities.domain.Status;
import com.lewis.msuser.entities.models.CategoryModel;
import com.lewis.msuser.entities.models.PlanModel;
import com.lewis.msuser.entities.models.UserModel;
import com.lewis.msuser.mockbeans.ConfigBeans;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.transaction.Transactional;
import java.util.UUID;
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
    private UserConvert userConvert;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CategoryModel categoryModel;

    @Autowired
    private PlanModel planModel;

    @Autowired
    private UserModel userModel;

    @Test
    @DisplayName("create return status 204")
    @Rollback(value = false)
    public void createReturnStatus204() throws Exception
    {

        categoryModel.setId(UUID.fromString("a76bbf8f-0f0b-4963-8bce-cabac88f667e"));
        categoryModel.setName("BASIC");

        userModel.setId(UUID.fromString("80433975-6f43-4426-9783-a5c833cd37e3"));
        userModel.setUsername("Matthew");
        userModel.setAge(19);
        userModel.setDoc("09393933");
        userModel.setEmail("matthew.js@gmail.com");
        planModel.setCategory(categoryModel);
        planModel.setStatus(Status.valueOf(1));
        userModel.setPlan(planModel);

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userModel)))
                .andExpect(status().isCreated());
    }

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
    @DisplayName("getAll returns IllegalArgumentException")
    public void getAllReturnsIllegalArgumentException() throws Exception
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
    @DisplayName("GetById return NotFoundId")
    public void getByIdReturnNotFoundId() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/users/{id}","72f3e5f0-83be-4a8e-88db-fc124230f022")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    @DisplayName("Delete returns 204")
    public void deleteReturns204() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/{id}", "82f3e5f0-83be-4a8e-88db-fc124230f022"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("GetById Returns NotFoundId")
    public void deleteReturnsNotFoundId() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/users/{id}","62f3e5f0-83be-4a8e-88db-fc124230f022"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",is("Invalid  User ID Not Found.")));
    }

    @Test
    @DisplayName("Patch Returns Request Method Not Supported")
    public void patchReturnsRequestMethodNotSupported() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.patch("/v1/users/{id}","62f3e5f0-83be-4a8e-88db-fc124230f022"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message",is("Request Method Not Supported")));
    }

}
