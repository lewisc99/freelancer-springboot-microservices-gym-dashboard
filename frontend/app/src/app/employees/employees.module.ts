import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeByIdComponent } from './employee-by-id/employee-by-id.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { SharedModule } from '../shared/shared.module';
import { AuthGuard } from '../shared/auth-guards/auth-guard/auth.guard';
import { RoleGuard } from '../shared/auth-guards/role-guard/role.guard';

var routes:Routes = [
    {path:"employees",component:EmployeeListComponent, canActivate:[AuthGuard, RoleGuard]},
    {path:'employees/create', component: EmployeeCreateComponent, canActivate:[AuthGuard, RoleGuard]},
    {path: 'employees/:id', component:EmployeeByIdComponent, canActivate:[AuthGuard, RoleGuard]},
    {path: 'employees/:id/edit', component:EmployeeUpdateComponent, canActivate:[AuthGuard, RoleGuard]},
    {path:'', redirectTo: 'employees', pathMatch: 'full'},
]

@NgModule({
    declarations: [
        EmployeeListComponent,
        EmployeeByIdComponent,
        EmployeeUpdateComponent,
        EmployeeCreateComponent,
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes),
        ReactiveFormsModule,
        SharedModule
    ]
})
export class EmployeeModule {}