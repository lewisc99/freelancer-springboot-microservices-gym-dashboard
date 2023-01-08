package com.lewis.msemployee.services;

import java.util.List;
import com.lewis.msemployee.config.exceptions.DatabaseException;
import com.lewis.msemployee.config.exceptions.ResourceNotFoundException;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Page;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
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
    public EmployeesDto getAll(Page page, String urlEmployee) {
       try
       {
           List<Employee> employeeList = employeeDao.getAll(page, urlEmployee);
           EmployeesDto employeesDto = new EmployeesDto();
           int employeeSize = employeeList.size();

           if (page.getPagNumber() < 1 && page.getSize() > 1)
           {
               page.setSize(1);
           }
           if((page.getPagNumber() <= 0) && (page.getSize() <= 0))
           {
               employeesDto.addEmployees(employeeList, urlEmployee);
               return employeesDto;
           }
           else
           {
               List<Employee> takeEmployees = employeeList.stream().skip((page.getPagNumber() - 1) * page.getSize()).limit(page.getSize()).toList();
               int employeeTotalPages = (int) Math.ceil((double) employeeSize / page.getSize());
               employeesDto.addPage(page.getSize(), employeeSize, employeeTotalPages, page.getPagNumber());
               employeesDto.addEmployees(takeEmployees, urlEmployee);

               return employeesDto;
           }

       }
       catch (NullPointerException exception)
       {
           throw new NullPointerException(exception.getMessage());
       }
       catch (DatabaseException exception)
       {
           throw new DatabaseException(exception.getMessage());
       }

       catch (Exception exception)
       {
           throw new RuntimeException(exception.getMessage());
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
