package com.lewis.msemployee.entities.dtos;
import com.lewis.msemployee.config.dtos.DtoConverter;
import com.lewis.msemployee.entities.domain.Employee;
import com.lewis.msemployee.entities.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class EmployeesDto {

    private List<EmployeeDto> _embedded = new ArrayList<>();

    private List<Link> links = new ArrayList<>();

    private Page page;

    public EmployeesDto(){}

    public List<EmployeeDto> get_embedded() {
        return _embedded;
    }

    public void set_embedded(List<EmployeeDto> _embedded) {
        this._embedded = _embedded;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public void addEmployees(List<Employee> employees, String url)
    {
        for(Employee employee: employees)
        {
            EmployeeDto employeeDto = DtoConverter.convertEmployeeToEmployeeDto(employee,"");
            _embedded.add(employeeDto);
        }
    }

    public void addPage(int size, int totalElements, int totalPages, int number)
    {
        this.page = new Page(size, totalElements, totalPages, number);
    }
}
