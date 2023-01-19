import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { UserListComponent } from '../employees/user-list/user-list.component';


var routes:Routes = [
    {path: 'users',component: UserListComponent  }
]


@NgModule({
    declarations: [

    ],
    imports: 
    [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forChild(routes)
    ]
})
export class UsersModule {}