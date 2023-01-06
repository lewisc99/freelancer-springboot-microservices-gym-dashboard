package com.lewis.msemployee.services;


import com.lewis.msemployee.config.exceptions.DatabaseException;
import com.lewis.msemployee.config.exceptions.ResourceNotFoundException;
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
    public void create(Employee employee)
    {
        try
        {
            employeeDao.create(employee);
        }
        catch (Exception e )
        {
            e.getStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Employee getById(UUID id) {

        try
        {
          return  employeeDao.getById(id);
        }
        catch (NullPointerException exception)
        {
            throw new NullPointerException();
        }
        catch (ResourceNotFoundException exception)
        {
            throw new ResourceNotFoundException(id);
        }
        catch (DatabaseException exception)
        {
            throw new DatabaseException(exception.getMessage());
        }
        catch (Exception exception)
        {
            exception.getStackTrace();
            throw new RuntimeException();
        }
    }
}
