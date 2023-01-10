package com.lewis.msemployee.services;
import com.lewis.msemployee.MsEmployeeApplication;
import com.lewis.msemployee.config.exceptions.DatabaseException;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.PageModel;
import com.lewis.msemployee.mockclasses.classesBeanConfig;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MsEmployeeApplication.class)
@Import(classesBeanConfig.class)
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

    private final List<Employee> employees = new ArrayList<>();

    @BeforeEach
    public void  beforeEach()
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

        Employee employee2 = new Employee();
        employee2.setId(UUID.fromString("4013346b-feb3-44c8-8e3d-234dc6235852"));
        employee2.setAge(22);
        employee2.setUsername("Gisele");
        employee2.setEmail("gisele@gmail.com");
        employee2.setDoc("055454888");
        employee2.setPassword("vida3788");

        roles.setId(UUID.fromString("6125011f-49fd-4cc8-a2d9-69e79ce127ab"));
        roles.setName("coach");
        employee2.setRoles(Arrays.asList(roles));

        employees.addAll(Arrays.asList(employee, employee2));

    }
    @Test
    @DisplayName("create employee")
    public void createEmployee()
    {
        Mockito.doNothing().when(employeeDao).create(employee);
        employeeService.create(employee);
        verify(employeeDao, times(1)).create(employee);
    }
    @Test
    @DisplayName("create employee throw RuntimeException Error")
    public void createEmployeeThrowRuntimeExceptionError()
    {
        Mockito.doThrow(new RuntimeException()).when(employeeDao).create(employee);
        assertThrows(RuntimeException.class, () -> employeeDao.create(employee));
    }

    @Test
    @DisplayName("getAll return employeesDto")
    public void getAllReturnEmployeesDto()
    {
        when(employeeDao.getAll("")).thenReturn(employees);

        PageModel pageModel = new PageModel(0,0,"");
        EmployeesDto result = employeeService.getAll(pageModel,"");
        assertEquals(2, result.get_embedded().size());
        assertNotNull(result);
    }

    @Test
    @DisplayName("getAll return EmployeesDto sort by Name")
    public void getAllReturnEmployeesDtoSortByName()
    {
        when(employeeDao.getAll("username")).thenReturn(employees);

        PageModel pageModel = new PageModel(0,0,"username");
        var employeesDto = employeeService.getAll(pageModel, "");
        var result = employeesDto.get_embedded().get(0).getUsername();
        assertEquals("Felipe",result);
    }

    @Test
    @DisplayName("getAll return EmployeesDto pagSize One pagNumber One")
    public void getAllReturnEmployeesDtoPagNumberOnePagSizeOne()
    {
        when(employeeDao.getAll("username")).thenReturn(employees);

        PageModel pageModel = new PageModel(1,1,"username");
        EmployeesDto result = employeeService.getAll(pageModel, "");
        assertEquals(1, result.get_embedded().size());
    }

    @Test
    @DisplayName("getAll return EmployeesDto pagNumber One and pagSize Two ")
    public void getAllReturnEmployeesDtoPagNumberOnePagSizeTwo()
    {
        when(employeeDao.getAll("username")).thenReturn(employees);

        PageModel pageModel = new PageModel(1,2,"username");
        EmployeesDto result = employeeService.getAll(pageModel, "");
        assertEquals(2, result.get_embedded().size());
    }

    @Test
    @DisplayName("getAll return EmployeesDto pagNumber One pagSize Zero")
    public void getAllReturnEmployeesDtoPagNumberTwoPagSizeZero()
    {
        when(employeeDao.getAll("username")).thenReturn(employees);

        PageModel pageModel = new PageModel(3,0,"username");
        EmployeesDto result = employeeService.getAll(pageModel, "");
        assertEquals(0, result.get_embedded().size());
    }
    @Test
    @DisplayName("getAll throws error Database Exception")
    public void getAllThrowsErrorDatabaseException()
    {
        when(employeeDao.getAll("")).thenThrow(new DatabaseException(""));

        PageModel pageModel = new PageModel(3,0,"");
        assertThrows( DatabaseException.class, () -> {employeeService.getAll(pageModel,"");});
    }

    @Test
    @DisplayName("getAll throws error NullPointException")
    public void getAllThrowsErrorNullPointException()
    {
        when(employeeDao.getAll("")).thenThrow(new NullPointerException());

        PageModel pageModel = new PageModel(3,0,"");
        assertThrows( NullPointerException.class, () -> {employeeService.getAll(pageModel,"");});
    }

    @Test
    @DisplayName("getAll throws error NullPointException")
    public void getAllThrowsErrorException()
    {
        when(employeeDao.getAll("")).thenThrow(new RuntimeException());

        PageModel pageModel = new PageModel(3,0,"");
        assertThrows( Exception.class, () -> {employeeService.getAll(pageModel,"");});
    }


    @Test
    @DisplayName("get throw an error")
    public void getThrowAnError()
    {
        when(employeeDao.getById(UUID.fromString("2413346b-feb3-44c8-8e3d-234dc6235852"))).thenThrow(new RuntimeException());

        assertThrows( RuntimeException.class, () -> {employeeService.getById(UUID.fromString("2413346b-feb3-44c8-8e3d-234dc6235852"));});
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


}
