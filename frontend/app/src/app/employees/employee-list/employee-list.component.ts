import { Component, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { ActivatedRoute } from '@angular/router';
import { DeleteModalComponent } from '../../shared/delete-modal/delete-modal.component';
import { PlaceholderDirective } from 'src/app/shared/directives/placeholder/placeholder.directive';
import { Subscription } from 'rxjs';
import { TokenStorageService } from '../../auth/services/token-storage/token-storage.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css',
    ]
})
export class EmployeeListComponent implements OnInit, OnDestroy {


  public employeesDto: EmployeesDto = new EmployeesDto();
  private closeSub:Subscription;
  public showModal:boolean = false;
  public messageDeleteModal:string = "";
  public id:string = "";

  constructor(private employeeService:EmployeeService, private activatedRoute:ActivatedRoute, private tokenStorage:TokenStorageService) {}

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe( () => this.getAll());
  }

    getAll(sortBy?:string,): void 
    {
        this.employeeService.getAll(sortBy).subscribe({
         next: (data:EmployeesDto) =>
          {
            console.log(this.tokenStorage.getToken())
            console.log(data);
            this.employeesDto = data;
          }, 
          error: (error:any) =>
          {
             alert(error);
          }
       });
    }

    showDeleteModal(id:string, username:string) 
    {
        this.messageDeleteModal = `Are you sure you want to delete ${username}?`;
        this.id = id;
        this.showModal = true;
    }
    
    closeDeleteModal()
    {
        this.getAll();
        this.messageDeleteModal = "";
        this.id = "";
        this.showModal = false;
    }

    ngOnDestroy(): void {
        if (this.closeSub)
        {
          this.closeSub.unsubscribe();
        }
    }


    deleteEmployee()
  {
    this.employeeService.delete(this.id).subscribe({
      next: () => this.closeDeleteModal(),
      error: (error) => alert(error)
    });
  }

}
