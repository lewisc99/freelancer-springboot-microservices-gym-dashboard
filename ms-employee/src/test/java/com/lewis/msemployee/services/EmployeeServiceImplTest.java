package com.lewis.msemployee.services;

import com.lewis.msemployee.MsEmployeeApplication;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@SpringBootTest(classes = MsEmployeeApplication.class)
public class EmployeeServiceImplTest {


    @MockBean
    private EmployeeDao employeeDao;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    @Qualifier("employee")
    private Employee employee;

    @Autowired
    private Roles roles;

    @BeforeEach
    public void  beforeEach()
    {
        employee.setId(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"));
        employee.setAge(20);
        employee.setUsername("Felipe");
        employee.setEmail("felipe@gmail.com");
        employee.setDoc("929393992");

        roles.setId(UUID.fromString("6125011f-49fd-4cc8-a2d9-69e79ce127ab"));
        roles.setName("admin");

        employee.setRoles(Arrays.asList(roles));
    }

    @Test
    @DisplayName("Assert username is equal to Felipe")
    public void getUserHasNameFelipe()
    {
        when(employeeDao.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"))).thenReturn(employee);

        assertEquals("Felipe", employeeService.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852")).getUsername());
    }

    @Test
    @DisplayName("get Employee is not null")
    public void getEmployeeIsNotNull()
    {
        when(employeeDao.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"))).thenReturn(employee);

        var result = employeeService.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"));

        assertNotNull(result);
    }
    @Test
    @DisplayName("get Employee Roles it's not null")
    public void getEmployeeRolesNotNull()
    {
        when(employeeDao.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"))).thenReturn(employee);

        var result = employeeService.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852")).getRoles();

        assertNotNull(result);
    }

    @Test
    @DisplayName("get throw an error")
    public void getThrowAnError()
    {
        when(employeeDao.getById(UUID.fromString("2413346b-feb3-44c8-8e3d-234dc6235852"))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> {employeeService.getById(UUID.fromString("2413346b-feb3-44c8-8e3d-234dc6235852"));});
    }

    @Test
    @DisplayName("create employee")
    public void createEmployee()
    {
        when(employeeDao.create(employee));

        var result = employeeService.getById(UUID.fromString("3413346b-feb3-44c8-8e3d-234dc6235852"));

        assertNotNull(result);
    }
}
