import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { UserListComponent } from './user-list/user-list.component';


var routes:Routes = [
    {path: 'users',component: UserListComponent  }
]


@NgModule({
    declarations: [
            UserListComponent
    ],
    imports: 
    [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes)
    ]
})
export class UsersModule {}