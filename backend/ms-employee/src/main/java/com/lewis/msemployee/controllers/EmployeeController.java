package com.lewis.msemployee.controllers;
import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.EmployeeModel;
import com.lewis.msemployee.entities.models.PageModel;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/employees")
@CrossOrigin(origins = "http://localhost:4200/")
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

    @GetMapping
    public ResponseEntity<EmployeesDto> getAll(@ModelAttribute  PageModel pageModel, HttpServletRequest request)
    {
        if(pageModel.getPagNumber() == null && pageModel.getPagSize() == null)
        {
            pageModel.setPagNumber(0);
            pageModel.setPagSize(0);
        }


        String fullUrl = request.getRequestURL().toString();
        String urlForEmployees = request.getRequestURI().toString();

        EmployeesDto employeesDto = employeeService.getAll(pageModel, fullUrl);

        if(!(pageModel.getPagNumber() <= 0 && pageModel.getPagSize() <= 0))
        {
            urlForEmployees += "?" + "pagNumber=" + pageModel.getPagNumber() + "&pagSize="+pageModel.getPagSize();
        }
        employeesDto.addLink(urlForEmployees, "GET-ALL-EMPLOYEES");


        return ResponseEntity.ok().body(employeesDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id, HttpServletRequest request)
    {
        Employee employee = employeeService.getById(id);
        String fullUrl = request.getRequestURL().toString();
        EmployeeDto employeeDto = DtoConverter.ToEmployeeDto(employee,fullUrl);

        return ResponseEntity.ok(employeeDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> update(@Valid @PathVariable UUID id,@Valid @RequestBody EmployeeModel employeeModel)
    {
        Boolean employee = employeeService.update(id, employeeModel);
        if(!employee)
        {
            return ResponseEntity.status(400).build();
        }
        return ResponseEntity.status(204).build();
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable UUID id)
    {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
