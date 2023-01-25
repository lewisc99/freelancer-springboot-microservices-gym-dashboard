import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';


const routes:Routes = [
  {path:"login", component:LoginComponent},
  {path:"", redirectTo:'login',pathMatch:'full'},
  {path:"**", redirectTo:'login',pathMatch:'full'}
]

@NgModule({
    declarations: [
    LoginComponent
  ],
    imports: [
        RouterModule.forChild(routes),
        ReactiveFormsModule,
        BrowserModule
    ],
    exports: []
})
export class AuthModule {}