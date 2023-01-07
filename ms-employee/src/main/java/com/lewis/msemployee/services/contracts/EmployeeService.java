package com.lewis.msemployee.services.contracts;

import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Page;
import com.lewis.msemployee.entities.dtos.EmployeesDto;
import java.util.UUID;
public interface EmployeeService {
    void create(Employee employee);
    EmployeesDto getAll(Page page, String urlEmployee);
    Employee getById(UUID id);


}
