import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeByIdComponent } from './employee-by-id/employee-by-id.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';
import { ReactiveFormsModule } from '@angular/forms';
import { EmployeeCreateComponent } from './employee-create/employee-create.component';
import { SharedModule } from '../shared/shared.module';


var routes:Routes = [
    {path:"employees",component:EmployeeListComponent},
    {path:'employees/create', component: EmployeeCreateComponent},
    {path: 'employees/:id', component:EmployeeByIdComponent},
    {path: 'employees/:id/edit', component:EmployeeUpdateComponent},
    {path:'', redirectTo: 'employees', pathMatch: 'full'}
]

@NgModule({
    declarations: [
        EmployeeListComponent,
        EmployeeByIdComponent,
        EmployeeUpdateComponent,
        EmployeeCreateComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes),
        ReactiveFormsModule,
        SharedModule
    ]
})
export class EmployeeModule {}