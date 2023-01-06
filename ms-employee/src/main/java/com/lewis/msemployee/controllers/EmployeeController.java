package com.lewis.msemployee.controllers;

import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
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

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id)
    {
        if (id == null)
        {
            return ResponseEntity.badRequest().build();
        }
        Employee employee = employeeService.getById(id);

        if (employee == null)
        {
            return ResponseEntity.notFound().build();
        }

        EmployeeDto employeeDto = DtoConverter.convertEmployeeToEmployeeDto(employee);
        return ResponseEntity.ok(employeeDto);
    }




}
