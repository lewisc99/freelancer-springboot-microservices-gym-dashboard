package com.lewis.msauthentication.controller;

import com.lewis.msauthentication.entities.domain.Employee;
import com.lewis.msauthentication.services.MyEmployeeDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="v1/employees")
public class EmployeeController {

    @Autowired
    private MyEmployeeDetailsService employeeDetailsService;

    @GetMapping(value="search")
    public ResponseEntity<Employee> findByEmail(@RequestParam(value="email") String email)
    {
        try
        {
            Employee employee = employeeDetailsService.findByEmail(email);
            return ResponseEntity.ok().body(employee);
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
