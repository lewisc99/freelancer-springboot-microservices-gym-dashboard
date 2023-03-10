package com.lewis.msemployee.config.dtos;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.entities.dtos.RolesDto;
import com.lewis.msemployee.mockclasses.classesBeanConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Import(classesBeanConfig.class)
public class DtoConverterTest {

    @Autowired
    private Employee employee;

    @Autowired
    private EmployeeDto employeeDto;

    @Autowired
    private Roles roleOne;

    @Autowired
    private Roles roleTwo;

    @Autowired
    private List<Roles> rolesList;


    @BeforeEach
    public void beforeEach()
    {
        employee.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        employee.setAge(18);
        employee.setDoc("19330329");
        employee.setEmail("jefferson@example.com");
        employee.setUsername("Jefferson");
        employee.setPassword("vida1234");

        Roles rolesAdmin = new Roles();
        rolesAdmin.setId(UUID.fromString("1052ba1c-8c5d-11ed-a1eb-0242ac120002"));
        rolesAdmin.setName("admin");
        employee.setRoles(Arrays.asList(rolesAdmin));

        employeeDto.setId(UUID.fromString("0852ba1c-8c5d-11ed-a1eb-0242ac120002"));
        employeeDto.setAge(18);
        employeeDto.setDoc("19330329");
        employeeDto.setEmail("jefferson@example.com");
        employeeDto.setUsername("Jefferson");

        employeeDto.addLinks("http://localhost:8001/api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4");

        roleOne.setId(UUID.fromString("ab7f3c91-6a92-429e-b090-b2e8754feb4a"));
        roleOne.setName("admin");

        roleTwo.setId(UUID.fromString("5a177d82-dba4-4150-bdef-ff1d26371582"));
        roleTwo.setName("coach");
        rolesList = new ArrayList<Roles>();
        rolesList.addAll(Arrays.asList(roleOne,roleTwo));

    }

    @DisplayName("employeeDto is not null")
    @Test
    public void employeeIsNotNull()
    {
        assertNotNull(employee);
    }

    @DisplayName("convert Employee To EmployeeDto")
    @Test
    public void convertEmployeeToEmployeeDtoNotNull()
    {
        EmployeeDto employeeDtoTwo = DtoConverter.ToEmployeeDto(employee,"http://localhost:8001/api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4");

        assertNotNull(employeeDtoTwo);
    }

    @DisplayName("convertEmployeeToEmployeeDto() assert return type is EmployeeDto")
    @Test
    public void assertReturnTypeIsEmployeeDto()
    {
        EmployeeDto result = DtoConverter.ToEmployeeDto(employee,"http://localhost:8001/api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4");

        assertEquals(result.getUsername(), employeeDto.getUsername());
        assertEquals(result.getDoc(), employeeDto.getDoc());
        assertEquals(result.getEmail(), employeeDto.getEmail());
        assertEquals(result.getId(), employeeDto.getId());
        assertEquals(result.getAge(), employeeDto.getAge());
    }

    @DisplayName("convertRolesToRolesDto() assert Returns List<RolesDto>")
    @Test
    public void convertRolesToRolesDtoReturnsListOfRolesDto()
    {
        List<RolesDto> result = DtoConverter.ToRolesDto(rolesList);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @DisplayName("converRolesToRolesDto() throws Error")
    @Test
    public void convertRolesToRolesDto()
    {
        List<RolesDto> result = DtoConverter.ToRolesDto(new ArrayList<>());

        assertNull(result);
    }
}
