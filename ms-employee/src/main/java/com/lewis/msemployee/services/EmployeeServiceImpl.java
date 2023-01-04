package com.lewis.msemployee.services;


import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public Employee getById(UUID id) {

        try
        {
          return  employeeDao.getById(id);
        }
        catch (Exception e)
        {
            e.getStackTrace();
            throw new RuntimeException();
        }
    }
}
