import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  RouterModule, Routes } from '@angular/router';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { EmployeeByIdComponent } from './employee-by-id/employee-by-id.component';


var routes:Routes = [
    {path:"employees",component:EmployeeListComponent},
    {path: 'employee/:id', component:EmployeeByIdComponent}
]

@NgModule({
    declarations: [
        EmployeeListComponent,
        EmployeeByIdComponent
    ],
    imports: [
        BrowserModule,
        RouterModule.forRoot(routes)
    ]
})
export class EmployeeModule {}