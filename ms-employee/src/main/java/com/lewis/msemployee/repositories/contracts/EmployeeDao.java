package com.lewis.msemployee.repositories.contracts;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Page;

import java.util.List;
import java.util.UUID;


public interface EmployeeDao {
    void create(Employee employee);
    Employee  getById(UUID id);

    List<Employee> getAll(Page page, String urlEmployee);


}
