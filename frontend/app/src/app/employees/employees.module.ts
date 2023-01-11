import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeByIdComponent } from './employee-by-id/employee-by-id.component';
import { EmployeeUpdateComponent } from './employee-update/employee-update.component';


var routes:Routes = [
    {path:"employees",component:EmployeeListComponent},
    {path: 'employees/:id', component:EmployeeByIdComponent},
    {path: 'employees/:id/edit', component:EmployeeUpdateComponent},
    {path:'', redirectTo: 'employees', pathMatch: 'full'}
]

@NgModule({
    declarations: [
        EmployeeListComponent,
        EmployeeByIdComponent,
        EmployeeUpdateComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes)
    ]
})
export class EmployeeModule {}