package com.lewis.msemployee.mockclasses;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.repositories.EmployeeDaoImpl;
import com.lewis.msemployee.services.EmployeeServiceImpl;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@ComponentScan(basePackages = {"com.lewis.msemployee"})
@TestConfiguration
public class classesBeanConfig {


    @Bean(name = "roles")
    @Scope(value = "prototype")
    public Roles getRoles() {
        return new Roles();
    }


    @Bean(name = "employee")
    @Scope(value = "prototype")
    public Employee getEmployee() {
        return new Employee();
    }

    @Bean(name="employeeDto")
    @Scope(value="prototype")
    public EmployeeDto getEmployeeDto()
    {
        return new EmployeeDto();
    }

    @Bean
    @Scope
    public EmployeesDto getEmployeesDto()  {return new EmployeesDto();}

}
