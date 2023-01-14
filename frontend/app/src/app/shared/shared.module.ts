import { NgModule } from "@angular/core";
import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from "./navbar/navbar.component";
import { DeleteModalComponent } from './delete-modal/delete-modal.component';
import { Routes, RouterModule } from '@angular/router';

const routes:Routes = [
    {path: "employees/:id/delete", component: DeleteModalComponent}
]


@NgModule({
    declarations: [NavbarComponent, DeleteModalComponent],
    imports: [BrowserModule, RouterModule.forChild(routes)],
    exports: [NavbarComponent, DeleteModalComponent]
})
export class SharedModule {}