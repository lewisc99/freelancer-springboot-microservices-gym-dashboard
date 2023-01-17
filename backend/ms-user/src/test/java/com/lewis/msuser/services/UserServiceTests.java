package com.lewis.msuser.services;

import com.lewis.msuser.MsUserApplication;
import com.lewis.msuser.entities.domain.Category;
import com.lewis.msuser.entities.domain.Plan;
import com.lewis.msuser.entities.domain.Status;
import com.lewis.msuser.entities.domain.User;
import com.lewis.msuser.mockbeans.ConfigBeans;
import com.lewis.msuser.repositories.UserRepository;
import com.lewis.msuser.services.contracts.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {MsUserApplication.class} )
@Import(ConfigBeans.class)
public class UserServiceTests {

    @Autowired
    private User user;

    @Autowired
    private User userTwo;

    @Autowired
    private Plan plan;

    @Autowired
    private Plan planTwo;

    @Autowired
    private Category category;

    @Autowired
    private  Category categoryTwo;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public List<User> users = new ArrayList<>();


    @BeforeEach
    public void beforeEach()
    {

        category.setId(UUID.fromString("bf892ce4-d7a2-4ab2-aaa7-65b4119f36fe"));
        category.setName("VIP");

        categoryTwo.setId(UUID.fromString("334e494f-8fdd-46bc-87f3-8e45e6e37cfb"));
        categoryTwo.setName("PREMIUM");

        java.util.Date date = new java.util.Date("2022/1/1");
        java.util.Date date2 = new java.util.Date("2022/1/1");

        plan.setId(UUID.fromString("27ae9628-a5f4-4fc8-8e1e-9daae86ec00c"));
        plan.setStatus(Status.valueOf(1));
        plan.setStart(date);
        plan.setFinish(date2);
        plan.setCategory(category);

        planTwo.setId(UUID.fromString("4dca5b67-0565-4d0c-b380-b096b64407de"));
        planTwo.setStatus(Status.valueOf(2));
        planTwo.setStart(date);
        planTwo.setFinish(date2);
        planTwo.setCategory(categoryTwo);

        user.setId(UUID.fromString("8fbe5a34-54c0-438c-875e-660d3935f7b8"));
        user.setUsername("Lewis");
        user.setAge(23);
        user.setDoc("454575888");
        user.setEmail("lewis.souza@gmail.com");
        user.setPlan(plan);

        userTwo.setId(UUID.fromString("7f1d4cca-37eb-4dd8-a0ca-2c6331c80d2a"));
        userTwo.setUsername("Rick");
        userTwo.setAge(22);
        userTwo.setDoc("554848777");
        userTwo.setEmail("rick.souza@gmail.com");
        userTwo.setPlan(planTwo);

        users.add(user);
        users.add(userTwo);
    }

    @Test
    @DisplayName("create User")
    public void createUser()
    {
        when(userRepository.save(user)).thenReturn(user);
        userService.create(user);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    @DisplayName("GetAll return Users")
    public void getAllReturnUsers()
    {
        int pagNumber = 1;
        int pagSize = 1;
        String sortBy = "username";

        Page<User> page = new PageImpl<>(users);
        Pageable paging = PageRequest.of(pagNumber, pagSize, Sort.by(sortBy));
        when(userRepository.findAll(paging)).thenReturn(page);
        List<User> result = userService.findAll(pagNumber,pagSize,sortBy);

        assertEquals(2,result.size());
    }

    @Test
    @DisplayName("GetAll Returns RuntimeException")
    public void getAllReturnsRuntimeException()
    {
        int pagNumber = 10;
        int pagSize = 1;
        String sortBy = "username";

        Pageable paging = PageRequest.of(pagNumber, pagSize, Sort.by(sortBy));
        when(userRepository.findAll(paging)).thenThrow(RuntimeException.class);

        assertThrows( RuntimeException.class, () -> {userService.findAll(pagNumber,pagSize, sortBy);});
    }
    @Test
    @DisplayName("GetAll Returns SortByUsername")
    public void getAllReturnsSortByUsername()
    {
        int pagNumber = 1;
        int pagSize = 2;
        String sortBy = "username";

        Page<User> page = new PageImpl<>(users);
        Pageable paging = PageRequest.of(pagNumber, pagSize, Sort.by(sortBy));
        when(userRepository.findAll(paging)).thenReturn(page);

        List<User> result = userService.findAll(pagNumber,pagSize,sortBy);

        assertEquals("Lewis", result.get(0).getUsername());
    }

    @Test
    @DisplayName("GetAll throws IllegalArgumentException")
    public void getAllThrowsIllegalArgumentException()
    {
        int pagNumber = 0;
        int pagSize = 1;
        String sortBy = "username";

        Pageable paging = PageRequest.of(pagNumber, pagSize, Sort.by(sortBy));
        when(userRepository.findAll(paging)).thenThrow(IllegalArgumentException.class);
        assertThrows(IllegalArgumentException.class, () -> {userService.findAll(pagNumber,pagSize,sortBy);});
    }

    @Test
    @DisplayName("getById Return User")
    public void getByIdReturnUser()
    {
        Optional<User> userOptional = Optional.ofNullable(user);
        when(userRepository.findById(UUID.fromString("8fbe5a34-54c0-438c-875e-660d3935f7b8"))).thenReturn(userOptional);

        User result = userService.findById(UUID.fromString("8fbe5a34-54c0-438c-875e-660d3935f7b8"));

        assertNotNull(result);
        assertEquals("Lewis",result.getUsername());
        assertEquals(23,result.getAge());
        assertEquals("454575888",result.getDoc());
        assertEquals("lewis.souza@gmail.com",result.getEmail());
    }

    @Test
    @DisplayName("getById Return User")
    public void getByIdThrowNullPointException()
    {
        Optional<User> userOptional = Optional.ofNullable(user);
        when(userRepository.findById(UUID.fromString("8fbe5a34-54c0-438c-875e-660d3935f7b8"))).thenReturn(userOptional);

        assertThrows(NullPointerException.class, () -> {userService.findById(UUID.fromString("7fbe5a34-54c0-438c-875e-660d3935f7b8"));});
    }


}
