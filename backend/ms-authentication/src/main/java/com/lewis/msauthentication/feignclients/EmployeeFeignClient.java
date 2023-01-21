package com.lewis.msauthentication.feignclients;


import com.lewis.msauthentication.entities.domain.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(name="ms-employee", path ="v1/employees")
public interface EmployeeFeignClient {

    @GetMapping(value="/search")
    ResponseEntity<Employee> findByEmail(@RequestParam String email);

}
