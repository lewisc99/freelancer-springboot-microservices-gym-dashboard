package com.lewis.msemployee.controllers;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Employee employee)
    {
        employee.setId(UUID.randomUUID());
        employeeService.create(employee);
        return ResponseEntity.status(201).build();

    }


}
