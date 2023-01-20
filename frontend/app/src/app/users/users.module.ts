import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserListComponent } from './user-list/user-list.component';
import { UserByIdComponent } from './user-by-id/user-by-id.component';
import { SharedModule } from '../shared/shared.module';
import { UserUpdateComponent } from './user-update/user-update.component';
import { UserCreateComponent } from './user-create/user-create.component';


var routes:Routes = [
    {path: 'users/create',component: UserCreateComponent},
    {path: 'users',component: UserListComponent  },
    {path: 'users/:id',component: UserByIdComponent  }
]


@NgModule({
    declarations: [
            UserListComponent,
            UserByIdComponent,
            UserUpdateComponent,
            UserCreateComponent
    ],
    imports: 
    [
        BrowserModule,
        ReactiveFormsModule,
        RouterModule.forRoot(routes),
        SharedModule,
        FormsModule
    ]
})
export class UsersModule {}