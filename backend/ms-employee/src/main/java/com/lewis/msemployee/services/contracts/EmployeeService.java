package com.lewis.msemployee.services.contracts;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import com.lewis.msemployee.entities.models.EmployeeModel;
import com.lewis.msemployee.entities.models.PageModel;

import java.util.UUID;
public interface EmployeeService {
    void create(Employee employee);
    EmployeesDto getAll(PageModel page, String urlEmployee);
    Employee getById(UUID id);

    Employee getByEmail(String email);

    Boolean update(UUID id, EmployeeModel employee);

    void delete(UUID id);

}
