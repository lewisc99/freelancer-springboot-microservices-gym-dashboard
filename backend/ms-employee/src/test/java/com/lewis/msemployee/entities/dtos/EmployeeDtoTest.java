package com.lewis.msemployee.entities.dtos;

import com.lewis.msemployee.mockclasses.classesBeanConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {classesBeanConfig.class})
public class EmployeeDtoTest {

    @Autowired
    EmployeeDto employeeDto;

    @Test
    @DisplayName("assert Url is not null")
    public void addLinksAssertUrlIsNotNull()
    {
        employeeDto.addLinks("http://localhost:api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4");
        assertNotNull(employeeDto.getLinks());
    }

    @Test
    @DisplayName("assert Links size is One")
    public void addLinksAssertLinkSizeIsOne()
    {
        employeeDto.addLinks("http://localhost:api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4", true);
        assertEquals(1 ,employeeDto.getLinks().size());
    }


    @Test
    @DisplayName("assert Links size is Three")
    public void addLinksAssertLinkSizeIsThree()
    {
        employeeDto.addLinks("http://localhost:api/employees/23304dc3-564e-45b3-b91b-905aa76b74c4");
        assertEquals(3,employeeDto.getLinks().size());
    }

}
