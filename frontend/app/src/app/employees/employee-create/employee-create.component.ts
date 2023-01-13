import { Component, OnInit } from '@angular/core';
import { RolesService } from '../../shared/services/roles.service';
import { Roles } from '../domain/entities/roles';

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {

  
  public roles:Roles[] = [];
  public checkBoxRoles:Map<string,boolean> = new Map<string,boolean>();

  constructor(private rolesService:RolesService) {}

  ngOnInit(): void {
     this.getRoles();
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
