import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';

@Component({
  selector: 'app-employee-by-id',
  templateUrl: './employee-by-id.component.html',
  styleUrls: ['./employee-by-id.component.css']
})
export class EmployeeByIdComponent implements OnInit, OnDestroy{

  constructor(private route:ActivatedRoute, private employeeService:EmployeeService){}

  private getByIdSubscription:Subscription;
  public employee:EmployeeDto = new EmployeeDto();

  ngOnInit(): void {

   this.getByIdSubscription = this.route.paramMap.subscribe(
      params =>  this.getById(params.get('id')!)
    )


  }

  private getById(id: string):void
  {
      this.employeeService.getById(id).subscribe(
        (result:EmployeeDto) =>
        {
            this.employee = result;
        },
        (error:any) =>
        {
          console.log(error);
        }
      )
  }



  ngOnDestroy(): void {
    this.getByIdSubscription.unsubscribe();
  }

}
