import { NgModule } from "@angular/core";
import { BrowserModule } from '@angular/platform-browser';
import { NavbarComponent } from "./navbar/navbar.component";
import { DeleteModalComponent } from './components/delete-modal/delete-modal.component';
import { Routes, RouterModule } from '@angular/router';
import { PlaceholderDirective } from './directives/placeholder/placeholder.directive';
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { TokenInterceptor } from "./interceptor/token-interceptor/token-interceptor.interceptor";
import { AuthGuard } from './auth-guards/auth-guard/auth.guard';
import { RoleGuard } from './auth-guards/role-guard/role.guard';
import { NotFoundComponent } from './components/not-found/not-found.component';

const routes:Routes = [
  
]

@NgModule({
    declarations: [NavbarComponent, DeleteModalComponent, PlaceholderDirective, NotFoundComponent],
    imports: [BrowserModule, RouterModule.forRoot(routes)],
    providers: [{provide: HTTP_INTERCEPTORS, useClass:TokenInterceptor, multi:true}, AuthGuard, RoleGuard],
    exports: [NavbarComponent, DeleteModalComponent]
})
export class SharedModule {}