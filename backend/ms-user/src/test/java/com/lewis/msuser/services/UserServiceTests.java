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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@SpringBootTest(classes = {MsUserApplication.class} )
@Import(ConfigBeans.class)
public class UserServiceTests {

    @Autowired
    private User user;

    @Autowired
    private Plan plan;

    @Autowired
    private Category category;

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

        java.util.Date date = new java.util.Date("2022/1/1");
        java.util.Date date2 = new java.util.Date("2022/1/1");

        plan.setId(UUID.fromString("27ae9628-a5f4-4fc8-8e1e-9daae86ec00c"));
        plan.setStatus(Status.valueOf(1));
        plan.setStart(date);
        plan.setFinish(date2);
        plan.setCategory(category);

        user.setId(UUID.fromString("37044977-af9b-41af-9d22-700ae3dd2406"));
        user.setUsername("Lewis");
        user.setAge(20);
        user.setDoc("199239393");
        user.setEmail("lewis.carlos@gmail.com");
        user.setPlan(plan);

        users.add(user);
    }

    @Test
    @DisplayName("GetAll return Users")
    public void getAllReturnUsers()
    {
        var pagNumber = 1;
        var pagSize = 1;
        Page<User> page = new PageImpl<>(users);
        Pageable paging = PageRequest.of(pagNumber, pagSize);
        when(userRepository.findAll(paging)).thenReturn(page);

        List<User> result = userService.findAll(1,1);

        assertEquals(1,result.size());

    }
}
