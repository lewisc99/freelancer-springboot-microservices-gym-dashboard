package com.lewis.msemployee.entities.domain;


import com.lewis.msemployee.MsEmployeeApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = MsEmployeeApplication.class)
public class EmployeeTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Employee employee;

    @BeforeEach
    public void beforeEach()
    {
        employee.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        employee.setAge(18);
        employee.setDoc("19330329");
        employee.setEmail("jefferson@example.com");
        employee.setUsername("Jefferson");
        employee.setPassword("vida1234");
    }

    @Test
    @DisplayName("Assert name is equal to Jefferson for Employee")
    public void assertNameIsEqualToJeffersonForEmployee()
    {
        assertEquals(
                "Jefferson", employee.getUsername()
        );
    }

    @Test
    @DisplayName("Assert Employee is not Null")
    public void assertEmployeeIsNotNull()
    {
        assertNotNull(employee);
    }

    @Test
    @DisplayName("Assert Employee properties not null")
    public void assertEmployeePropertiesNotNull()
    {
        Employee employeeBean = applicationContext.getBean("employee",Employee.class);

        employeeBean.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        employeeBean.setAge(21);
        employeeBean.setDoc("1505464");
        employeeBean.setEmail("anderson@example.com");
        employeeBean.setUsername("Anderson");
        employeeBean.setPassword("vida1234");

        assertNotNull(employeeBean.getUsername());
        assertNotNull(employeeBean.getAge());
        assertNotNull(employeeBean.getEmail());
        assertNotNull(employeeBean.getPassword());
        assertNotNull(employeeBean.getId());
        assertNotNull(employeeBean.getDoc());


    }

}
