import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';
import { RolesService } from '../../shared/services/roles.service';
import { Roles } from '../domain/entities/roles';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { EmployeeModel } from '../domain/models/employee.model';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit, OnDestroy{

  public id:string;
  private getIdSubscription:Subscription = new Subscription();
  public employee:EmployeeDto = new EmployeeDto();
  public employeeModel:EmployeeModel = new EmployeeModel();
  public rolesDto: Roles[] = [];
  public formGroup:FormGroup;

  constructor(private route:ActivatedRoute, private employeeService:EmployeeService, private rolesService:RolesService, private fb:FormBuilder) {}

  ngOnInit(): void {

      this.getIdSubscription = this.route.paramMap.subscribe( 
        (params:any) =>
        {
          var id = params.get('id');
          this.getEmployeeById(id);
        }
      )
      this.getAllRoles();
  }

  getAllRoles():void 
  {
    this.rolesService.getAll().subscribe(
      (result:Roles[]) =>
      {
        this.rolesDto = result;
        this.seedFormGroup();
      },
      (error:any) =>
      {
        console.log(error);
      }
    )
  }
  
  seedFormGroup():void 
  {
    this.formGroup = this.fb.group(
      {
        employeeModel: this.fb.group({
          id: new FormControl(this.employee.id),
          username: new FormControl(this.employee.username,[Validators.required]),
          email: new FormControl(this.employee.username,[Validators.required]),
          age: new FormControl(this.employee.age,[Validators.required]),
          doc: new FormControl(this.employee.doc,[Validators.required]),
           role: this.fb.group  ({
           }),
        })
      });

     const rolesForm = this.formGroup.get("employeeModel")?.get("role") as FormGroup;
     for (let value of this.rolesDto)
     {
      rolesForm.addControl(value.name, new FormControl("",[]));
     }
  }

  getEmployeeById(id:string): void
  {
      this.employeeService.getById(id).subscribe
      (
        (result:EmployeeDto) => 
        {
          this.employee = result;
          this.seedFormGroup();
        }, 
        (error:any) =>
        {
          console.log(error);
        }
      )
  }



  updateEmployee()
  {
    console.log(this.formGroup);
  }

  
  ngOnDestroy(): void {

    this.getIdSubscription.unsubscribe();
  }

}
