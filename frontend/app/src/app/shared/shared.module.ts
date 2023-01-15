import { NgModule } from "@angular/core";
import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from "./navbar/navbar.component";
import { DeleteModalComponent } from './delete-modal/delete-modal.component';
import { Routes, RouterModule } from '@angular/router';
import { PlaceholderDirective } from './directives/placeholder/placeholder.directive';

const routes:Routes = [
]


@NgModule({
    declarations: [NavbarComponent, DeleteModalComponent, PlaceholderDirective],
    imports: [BrowserModule, RouterModule.forChild(routes)],
    exports: [NavbarComponent, DeleteModalComponent]
})
export class SharedModule {}