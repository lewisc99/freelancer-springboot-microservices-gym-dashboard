import { Component, EventEmitter, Input, Output, OnInit } from '@angular/core';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent {

  @Input() message:string;
  @Input() id:string;
  @Output() close = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }

  deleteEmployee()
  {
    console.log(this.message);
    console.log(this.id);
  }

}
