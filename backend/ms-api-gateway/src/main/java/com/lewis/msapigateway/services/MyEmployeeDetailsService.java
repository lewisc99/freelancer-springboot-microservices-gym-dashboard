package com.lewis.msapigateway.services;

import com.lewis.msapigateway.entities.domain.Employee;
import com.lewis.msapigateway.feignclients.EmployeeFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyEmployeeDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeFeignClient employeeFeignClient;
    private static Logger logger = LoggerFactory.getLogger(MyEmployeeDetailsService.class);


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = findByEmail(email);
        return employee;
    }

    public Employee findByEmail(String email)
    {
        Employee employee = employeeFeignClient.findByEmail(email).getBody();

        if(employee == null)
        {
            logger.error("Email not found: " + email);
            throw new IllegalArgumentException("email not found");
        }
        logger.info("Email found: " + email);
        return employee;
    }
}
