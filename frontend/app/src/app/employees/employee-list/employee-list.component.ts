import { Component, ComponentFactoryResolver, ComponentRef, OnInit, ViewChild, OnDestroy } from '@angular/core';
import { EmployeeService } from '../employee-service/employee.service';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { ActivatedRoute } from '@angular/router';
import { DeleteModalComponent } from '../../shared/delete-modal/delete-modal.component';
import { PlaceholderDirective } from 'src/app/shared/directives/placeholder/placeholder.directive';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css',
    ]
})
export class EmployeeListComponent implements OnInit, OnDestroy {

  constructor(private employeeService:EmployeeService, private activatedRoute:ActivatedRoute,
    private componentFactoryResolver:ComponentFactoryResolver) {}

  public employeesDto: EmployeesDto = new EmployeesDto();
  @ViewChild(PlaceholderDirective) alertHost: PlaceholderDirective;
  private closeSub:Subscription;
  public showModal:boolean = false;
  public messageDeleteModal:string = "";
  public id:string = "";

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

    private deleteModal(message:string, id:string)
    {
      const deleteModalComponentFactory = this.componentFactoryResolver.resolveComponentFactory(DeleteModalComponent);
      const hostViewContainerRef = this.alertHost.viewContainerRef;
      hostViewContainerRef.clear();

      const componentRef = hostViewContainerRef.createComponent(deleteModalComponentFactory);
      componentRef.instance.message = message;
      componentRef.instance.id = id;

      this.closeSub = componentRef.instance.close.subscribe(
        () => 
        {
          this.closeSub.unsubscribe();
          hostViewContainerRef.clear();
        }
      )
    }

    showDeleteModal(id:string, username:string) 
    {
        this.messageDeleteModal =  `Are you sure you want to delete ${username}?`;
        this.id = id;
        this.showModal = true;
    }
    
    closeModal()
    {
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

}
