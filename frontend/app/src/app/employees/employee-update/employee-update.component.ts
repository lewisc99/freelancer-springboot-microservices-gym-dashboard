import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';
import { RolesService } from '../../shared/services/roles.service';
import { Roles } from '../domain/entities/roles';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit, OnDestroy{

  constructor(private route:ActivatedRoute, private employeeService:EmployeeService, private rolesService:RolesService) {}

  public id:string;
  private getIdSubscription:Subscription = new Subscription();
  public employee:EmployeeDto = new EmployeeDto();
  public rolesDto: any[];

  ngOnInit(): void {

      this.getIdSubscription = this.route.paramMap.subscribe( 
        (params:any) =>
        {
          var id = params.get('id');
          this.getEmployeeById(id);
          this.getAllRoles();
        }
      )
  }

  ngOnDestroy(): void {

    this.getIdSubscription.unsubscribe();
  }

  getEmployeeById(id:string): void
  {
      this.employeeService.getById(id).subscribe
      (
        (result:EmployeeDto) => this.employee = result, 
        (error:any) =>
        {
          console.log(error);
        }
      )
  }

  getAllRoles():void 
  {
    this.rolesService.getAll().subscribe(
      (result:Roles[]) =>
      {
        this.rolesDto = result;
      },
      (error:any) =>
      {
        console.log(error);
      }
    )
  }


  

}
