package com.lewis.msemployee.mockclasses;


import com.lewis.msemployee.entities.domain.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class classesBeanConfig {

    @Bean(name="employee")
    @Scope(value="prototype")
    Employee getEmployee()
    {
        return new Employee();
    }

}
