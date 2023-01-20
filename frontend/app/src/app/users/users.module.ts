import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { UserListComponent } from './user-list/user-list.component';
import { UserByIdComponent } from './user-by-id/user-by-id.component';


var routes:Routes = [
    {path: 'users',component: UserListComponent  },
    {path: 'users/:id',component: UserByIdComponent  }
]


@NgModule({
    declarations: [
            UserListComponent,
            UserByIdComponent
    ],
    imports: 
    [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes)
    ]
})
export class UsersModule {}