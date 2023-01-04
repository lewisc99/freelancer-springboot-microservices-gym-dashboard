package com.lewis.msemployee.services.contracts;

import com.lewis.msemployee.entities.domain.Employee;

import java.util.UUID;

public interface EmployeeService {

    Employee getById(UUID id);

}
