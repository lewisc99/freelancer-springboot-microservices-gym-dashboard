package com.lewis.msemployee.config.dtos;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Roles;
import com.lewis.msemployee.entities.dtos.EmployeeDto;
import com.lewis.msemployee.entities.dtos.RolesDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class DtoConverter {

    public static EmployeeDto convertEmployeeToEmployeeDto( Employee employee, String url, boolean... fromEmployees)
    {
      EmployeeDto employeeDto =  new EmployeeDto(employee.getId(), employee.getUsername(), employee.getAge(), employee.getDoc(), employee.getEmail(), employee.getRoles());
      employeeDto.addLinks(url + "/" + employee.getId(), fromEmployees);
      return employeeDto;
    }

    public static List<RolesDto> convertRolesToRolesDto(List<Roles> roles)
    {
        List<RolesDto> rolesDto = new ArrayList<>();
        for(var role: roles)
        {
            RolesDto roleDto = new RolesDto(role.getId(), role.getName());
            rolesDto.add(roleDto);
        }
        return rolesDto;
    }
}
