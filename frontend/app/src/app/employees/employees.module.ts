import { NgModule } from '@angular/core';
import { EmployeesListComponent } from './employees-list/employees-list.component';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';


var routes:Routes = [
    {path:"employee-list",component:EmployeesListComponent}
]

@NgModule({
    declarations: [
        EmployeesListComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes)
    ]
})
export class EmployeeModule {}