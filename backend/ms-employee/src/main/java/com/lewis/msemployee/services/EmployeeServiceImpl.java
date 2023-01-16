package com.lewis.msemployee.services;
import java.util.List;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.EmployeeModel;
import com.lewis.msemployee.entities.models.PageModel;
import com.lewis.msemployee.repositories.contracts.EmployeeDao;
import com.lewis.msemployee.services.contracts.EmployeeService;
import com.lewis.msemployee.services.contracts.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl  implements EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private RoleService roleService;


    @Override
    public void create(Employee employee)
    {
        try
        {
            employeeDao.create(employee);
        }
        catch (Exception e )
        {
            throw new RuntimeException();
        }
    }

    @Override
    public EmployeesDto getAll(PageModel page, String urlEmployee) {

           List<Employee> employeeList = employeeDao.getAll(page.getSortBy());
           EmployeesDto employeesDto = new EmployeesDto();
           int employeeSize = employeeList.size();

           setDefaultPageModel(page);

           if((page.getPagNumber() <= 0) && (page.getPagSize() <= 0))
               employeesDto.addEmployees(employeeList, urlEmployee);
           else
               convertToHateoasPagination(page, urlEmployee, employeeList, employeesDto, employeeSize);

           return employeesDto;
    }
    private static void setDefaultPageModel(PageModel page) {
        if (page.getPagNumber() == null || page.getPagSize() == null)
        {
            page.setPagNumber(0);
            page.setPagSize(0);
        }
        if (page.getPagNumber() < 1 && page.getPagSize() > 1)
            page.setPagNumber(1);
    }
    private  void convertToHateoasPagination(PageModel page, String urlEmployee, List<Employee> employeeList, EmployeesDto employeesDto, int employeeSize) {
        var takeEmployeesStream = employeeList.stream().skip( (page.getPagNumber() - 1) * page.getPagSize()).limit(page.getPagSize());
        List<Employee> takeEmployeesList = List.of(takeEmployeesStream.toArray(Employee[]::new));
        int employeeTotalPages = (int) Math.ceil((double) employeeSize / page.getPagSize());
        employeesDto.addPage(page.getPagSize(), employeeSize, employeeTotalPages, page.getPagNumber());
        employeesDto.addEmployees(takeEmployeesList, urlEmployee);
    }

    @Override
    public Employee getById(UUID id) {
        try
        {
            return  employeeDao.getById(id);
        }
        catch (NullPointerException e)
        {
            throw new NullPointerException();
        }
    }

    @Override
    public Boolean update(UUID id, EmployeeModel employee)
    {
        Employee employeeById = getById(id);

        if (employeeById == null)
            throw new NullPointerException();

        Employee updatedEmployee = handleUpdateEmployee(employee, employeeById);
        Boolean result =  employeeDao.update(updatedEmployee);
        if(!result)
            throw new RuntimeException();

        return true;
    }

    @Override
    public void delete(UUID id)
    {
       Employee employee = getById(id);
       if(employee == null)
           throw new NullPointerException();

       Boolean result = employeeDao.delete(employee);
       if (!result)
           throw new NullPointerException();
    }

    public Employee handleUpdateEmployee(EmployeeModel updateEmployee, Employee oldEmployee)
    {
        oldEmployee.setUsername(updateEmployee.getUsername());
        oldEmployee.setAge(updateEmployee.getAge());
        oldEmployee.setEmail(updateEmployee.getEmail());
        oldEmployee.setDoc(updateEmployee.getDoc());

        List<Roles> roles =  roleService.findRolesByName(updateEmployee.getRoles());
        oldEmployee.setRoles(roles);
        return oldEmployee;
    }


}
