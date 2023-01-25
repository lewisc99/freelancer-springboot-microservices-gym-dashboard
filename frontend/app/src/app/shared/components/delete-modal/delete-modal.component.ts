import { Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-delete-modal',
  templateUrl: './delete-modal.component.html',
  styleUrls: ['./delete-modal.component.css']
})
export class DeleteModalComponent {


  @Input() message:string;
  @Output() close = new EventEmitter<void>();
  @Output() emitDeleted = new EventEmitter<void>();

  onClose() {
    this.close.emit();
  }

  onDelete()
  {
      this.emitDeleted.emit();
  }

}
