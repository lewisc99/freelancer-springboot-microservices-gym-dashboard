import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Subscription } from 'rxjs';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';
import { RolesService } from '../../shared/services/roles.service';
import { Roles } from '../domain/entities/roles';
import { FormArray, FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
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
  public checkBoxRoles:Map<string,boolean> = new Map<string,boolean>();
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
      {
       next: (result:Roles[]) =>
        {
          this.rolesDto = result;
          for (var item of this.rolesDto)
          {
            this.checkBoxRoles.set(item.name, false);
          }
          this.seedFormGroup();
        },
        error: (error:any) =>
        {
          console.log(error);
        }
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
          email: new FormControl(this.employee.email,[Validators.required]),
          age: new FormControl(this.employee.age,[Validators.required]),
          doc: new FormControl(this.employee.doc,[Validators.required]),
           role: this.fb.array  ([]),
        })
      });

    const rolesForm = this.formGroup.get("employeeModel")?.get("role") as FormArray;
    for (var item of this.employee.roles)
    {
       rolesForm.push(new FormControl(item.name,[]));
       this.checkBoxRoles.set(item.name,true);
    };
  }

  getEmployeeById(id:string): void
  {
      this.employeeService.getById(id).subscribe
      (
        {
          next: (result:EmployeeDto) =>
          {
          this.employee = result;
          this.seedFormGroup();
          },
          error: error =>
          {
              console.log(error);
          }
        }
      )
  }



  onSubmit()
  {
     var rolesModel = this.formGroup.controls['employeeModel'].value;
     console.log(rolesModel.role);
     console.log(this.checkBoxRoles);
    console.log(rolesModel);
  }

  onRoleAdded(role:any)
  {
    const rolesForm = this.formGroup.get("employeeModel")?.get("role") as FormArray;
    const employeeHasRole:string[] = rolesForm.value;

    var getRole = this.checkBoxRoles.get(role);
    var roleInList =  employeeHasRole.findIndex(s => s == role);
    if (getRole && (roleInList >= 0))
    {
      this.checkBoxRoles.set(role,false);
      employeeHasRole.splice(roleInList,1);
    }
    else
    {
      this.checkBoxRoles.set(role,true);
      employeeHasRole.push(role);
    }
     console.log(employeeHasRole);
   
  }

  
  ngOnDestroy(): void {

    this.getIdSubscription.unsubscribe();
  }

}
