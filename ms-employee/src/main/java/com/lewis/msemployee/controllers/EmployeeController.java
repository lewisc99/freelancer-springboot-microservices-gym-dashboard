package com.lewis.msemployee.controllers;
import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/employees")
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
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id, HttpServletRequest request)
    {
        if (id == null)
        {
            return ResponseEntity.badRequest().build();
        }

        Employee employee = employeeService.getById(id);
        String fullUrl = request.getRequestURL().toString();
        EmployeeDto employeeDto = DtoConverter.convertEmployeeToEmployeeDto(employee,fullUrl);

        return ResponseEntity.ok(employeeDto);
    }

}
