package com.lewis.msemployee.config.dtos;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public static EmployeeDto convertEmployeeToEmployeeDto( Employee employee)
    {
        return new EmployeeDto(employee.getId(), employee.getUsername(), employee.getAge(), employee.getDoc(), employee.getEmail(), employee.getRoles());
    }
}
