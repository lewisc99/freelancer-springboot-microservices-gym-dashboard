package com.lewis.msemployee.config.data;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.UUID;

@Configuration
@Profile("test")
public class SeedDataTestConfig implements CommandLineRunner {
    @Autowired
    private EmployeeService employeeDao;

    @Override
    public void run(String... args) throws Exception {
        Employee employee1 = new Employee(UUID.fromString("23304dc3-564e-45b3-b91b-905aa76b74c4"),"lewis",23, "92929393", "lewis@example.com", "vida1234");
        Employee employee2 = new Employee(UUID.fromString("23a8dbd6-9331-4b12-986b-047c6614c0cd"),"rick", 21, "44155454", "rick@example.com", "vida1234");
        Employee employee3 = new Employee(UUID.fromString("8873543e-7b01-4f89-936b-278e887861a0"),"gabriel", 24, "441554542", "gabriel@example.com", "vida1234");
        Employee employee4 = new Employee(UUID.fromString("b50296b0-7a0c-462b-bf84-41986a4df5ea"),"mary", 50, "0845857588", "mary@example.com", "vida1234");
        Employee employee5 = new Employee(UUID.fromString("9a6ad380-be45-49c0-b617-4f3e1a9a4aaf"),"luis Claudio", 27, "05488877", "luis@example.com", "vida1234");

        Roles role1 = new Roles(UUID.fromString("744bd78e-7ddf-4e24-a644-fd63f283885f"),"admin");
        Roles role2 = new Roles(UUID.fromString("c3cc75cb-b925-4d91-9f51-f9d2bbe6ae08"),"cleaner");
        Roles role3 = new Roles(UUID.fromString("362e2813-e237-4bc8-9fdf-c3904e37db17"),"coach");
        Roles role4 = new Roles(UUID.fromString("402433ee-1b4d-4566-856d-27efbfb34d5a"),"manager");

        employee1.setRoles(Arrays.asList(role1, role3, role4));
        employee2.setRoles(Arrays.asList(role3));
        employee3.setRoles(Arrays.asList(role3));
        employee4.setRoles(Arrays.asList(role2));
        employee5.setRoles(Arrays.asList(role1));

        employeeDao.create(employee1);
        employeeDao.create(employee2);
        employeeDao.create(employee3);
        employeeDao.create(employee4);
        employeeDao.create(employee5);
    }
}
