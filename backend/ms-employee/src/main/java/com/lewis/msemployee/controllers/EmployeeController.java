package com.lewis.msemployee.controllers;
import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.EmployeeModel;
import com.lewis.msemployee.entities.models.PageModel;
import com.lewis.msemployee.services.contracts.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("v1/employees")
@OpenAPIDefinition(info = @Info(title = "EmployeeController", version = "1", description = "This an Employee Controller to get all information about Employees"))
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private BCryptPasswordEncoder  passwordEncoder;

    @ApiOperation(value="create a new Employee",
            notes = "will create a new Employee, you must add valid properties",
            response = Void.class, code = 201
    )
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Employee employee)
    {
        UUID uuid = UUID.randomUUID();
        employee.setId(uuid);
        employeeService.create(employee);
        return ResponseEntity.status(201).build();
    }
    private void passwordEncoder(Employee employee) {
        String PasswordEncoded = passwordEncoder.encode(employee.getPassword());
        employee.setPassword(PasswordEncoded);
    }

    @ApiOperation(value="get all Employees",
            notes = "you must add parameters pagNumber and pagSize and sortBy",
            response = EmployeesDto.class, code = 200
    )
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

    @ApiOperation(value="get Employee By Id",
            notes = "you must add a valid Id of type UUID",
            response = EmployeeDto.class, code = 200
    )
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable UUID id, HttpServletRequest request)
    {
        Employee employee = employeeService.getById(id);
        String fullUrl = request.getRequestURL().toString();
        EmployeeDto employeeDto = DtoConverter.ToEmployeeDto(employee,fullUrl);

        return ResponseEntity.ok(employeeDto);
    }

    @ApiOperation(value="to get Employees By Email",
            notes = "This method is called by another microservices to get information about the Employee By Email",
            response = Void.class, code = 200
    )
    @GetMapping("search")
    public ResponseEntity<Employee> getByEmail(@RequestParam(value = "email") String email)
    {
        Employee employee = employeeService.getByEmail(email);
        System.out.println("Employee by email called");
        return ResponseEntity.ok(employee);
    }

    @ApiOperation(value="to Update an Employee",
            notes = "you must add a valid Id in the URl and valid properties, it's not necessary to add the IDS in the body",
            response = Void.class, code = 204
    )
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

    @ApiOperation(value="to Delete an Employee",
            notes = "you must add a valid Id type of UUID",
            response = Void.class, code = 204
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@Valid @PathVariable UUID id)
    {
        employeeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
