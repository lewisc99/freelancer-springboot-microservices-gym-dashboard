package com.lewis.msemployee.config.dtos;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public static EmployeeDto convertEmployeeToEmployeeDto( Employee employee, String url, boolean... fromEmployees)
    {
      EmployeeDto employeeDto =  new EmployeeDto(employee.getId(), employee.getUsername(), employee.getAge(), employee.getDoc(), employee.getEmail(), employee.getRoles());
      employeeDto.addLinks(url + "/" + employee.getId(), fromEmployees);
      return employeeDto;
    }
}
