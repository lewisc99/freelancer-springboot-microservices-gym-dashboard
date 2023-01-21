package com.lewis.msemployee.repositories.contracts;
import com.lewis.msemployee.entities.domain.Employee;

import java.util.List;
import java.util.UUID;


public interface EmployeeDao {
    void create(Employee employee);
    Employee  getById(UUID id);
    List<Employee> getAll( String sortBy);

    Boolean update(Employee employee);

    Boolean delete(Employee delete);

    Employee getByEmail(String email);

}
