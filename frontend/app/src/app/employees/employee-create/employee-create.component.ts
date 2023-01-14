import { Component, OnInit } from '@angular/core';
import { RolesService } from '../../shared/services/roles.service';
import { Roles } from '../domain/entities/roles';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  
  public roles:Roles[] = [];
  public checkBoxRoles:Map<string,boolean> = new Map<string,boolean>();
  public formGroup:FormGroup;

  constructor(private rolesService:RolesService, private fb:FormBuilder) {}

  ngOnInit(): void {
     this.getRoles();
     this.initiateFormGroup();
  }

 private initiateFormGroup(): void
 {
    this.formGroup = this.fb.group(
      {
        employeeModel: this.fb.group({
          id: new FormControl(),
          username: new FormControl(),
          email: new FormControl(),
          age: new FormControl(),
          doc: new FormControl(),
           roles: this.fb.array  ([])
        })
      }
    )

 }


  getRoles()
  {
      this.rolesService.getAll().subscribe(
        {
          next: (result:Roles[]) =>
          {
               this.roles = result;
          },
          error: (error:any) =>
          {
            console.log(error);
          }
        }
      )
  }

  onRolesAdded(roleName:string)
  {
   
  }

}
