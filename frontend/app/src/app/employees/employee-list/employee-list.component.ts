import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css',
    ]
})
export class EmployeeListComponent implements OnInit {

  constructor(private employeeService:EmployeeService, private activatedRoute:ActivatedRoute) {}

  public employeesDto: EmployeesDto = new EmployeesDto();


  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      () => {this.getAll();}
    );
  }

    getAll(): void 
    {
        this.employeeService.getAll().subscribe({
         next: (data:EmployeesDto) =>
          {
            console.log(data);
            this.employeesDto = data;
          }, 
          error: (error:any) =>
          {
            console.log(error);
          }
    });
    }

}
