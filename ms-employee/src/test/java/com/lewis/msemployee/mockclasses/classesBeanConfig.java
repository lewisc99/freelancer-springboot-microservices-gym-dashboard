package com.lewis.msemployee.mockclasses;
import com.lewis.msemployee.entities.domain.Roles;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan(basePackages = {"com.lewis.msemployee"})
public class classesBeanConfig {


    @Bean(name = "roles")
    @Scope(value = "prototype")
    public Roles getRoles() {
        return new Roles();
    }


    @Bean(name = "employee")
    @Scope(value = "prototype")
    public Roles getEmployee() {
        return new Roles();
    }

}
