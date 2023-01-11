import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css',
    ]
})
export class EmployeeListComponent implements OnInit {

  constructor(private employeeService:EmployeeService) {}

  public employeesDto: EmployeesDto = new EmployeesDto();


  ngOnInit(): void {
      this.getAll();
  }

    getAll(): void 
    {
        this.employeeService.getAll().subscribe(
          (data:EmployeesDto) =>
          {
            console.log(data);
            this.employeesDto = data;
          }, 
          (error:any) =>
          {
            console.log(error);
          }
        );
    }

}
