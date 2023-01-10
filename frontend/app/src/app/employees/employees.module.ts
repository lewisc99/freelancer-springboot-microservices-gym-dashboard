import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';


var routes:Routes = [
    {path:"employee-list",component:EmployeeListComponent}
]

@NgModule({
    declarations: [
        EmployeeListComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes)
    ]
})
export class EmployeeModule {}