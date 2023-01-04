package com.lewis.msemployee.entities.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = Roles.class)
public class RoleTest {


    @Autowired
    private  Roles roles;

    @Autowired
    ApplicationContext context;

    @BeforeEach
    public void beforeEach()
    {
        roles.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        roles.setName("cleaner");
    }

    @Test
    @DisplayName("Assert role is not null")
    public void assertRoleIsNotNull()
    {
        assertNotNull(roles);
    }

    @Test
    @DisplayName("assert Name Is cleaner")
    public void assertNameIscleaner()
    {
        assertEquals(roles.getName() ,"cleaner");
    }

    @Test
    @DisplayName("Assert role properties not null")
    public void assertRolePropertiesNotNull()
    {
        Roles rolesBean = context.getBean( "roles", Roles.class);

        rolesBean.setName("admin");
        assertNotNull(rolesBean.getName());

        rolesBean.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        assertNotNull(rolesBean.getId());
    }
    @Test
    @DisplayName("Role name size is greater than five")
    public void assertRoleNameSizeIsGreaterThanFive()
    {
        Roles rolesBean = context.getBean("roles", Roles.class);

        rolesBean.setName("admin");
        rolesBean.getName();
        assertNotNull(rolesBean.getName());

        var result = rolesBean.getName().length();
        assertTrue(result  >= 5);
    }



}
