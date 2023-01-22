package com.lewis.msapigateway.feignclients;

import com.lewis.msapigateway.entities.domain.Employee;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
@FeignClient(name="ms-employee")
public interface EmployeeFeignClient {

    @GetMapping(value="v1/employees/search")
    ResponseEntity<Employee> findByEmail(@RequestParam(value="email") String email);

}
