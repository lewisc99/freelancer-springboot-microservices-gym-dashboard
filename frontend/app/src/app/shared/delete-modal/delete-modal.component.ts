import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';
import { EmployeeService } from '../../employees/employee-service/employee.service';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent {

  constructor(private EmployeeService:EmployeeService){}

  @Input() message:string;
  @Input() id:string;
  @Output() close = new EventEmitter<void>();
  @Output() employeeDeleted = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }

  deleteEmployee()
  {
    this.EmployeeService.delete(this.id).subscribe({
      next: () => {this.employeeDeleted.emit()},
      error: error => alert(error)
    });

  }

}
