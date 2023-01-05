package com.lewis.msemployee.repositories.contracts;

import com.lewis.msemployee.entities.domain.Employee;
import java.util.UUID;


public interface EmployeeDao {

    Employee  getById(UUID id);

    void create(Employee employee);

}
