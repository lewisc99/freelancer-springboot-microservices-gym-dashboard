import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { UserListComponent } from './user-list/user-list.component';
import { UserByIdComponent } from './user-by-id/user-by-id.component';
import { SharedModule } from '../shared/shared.module';
import { UserUpdateComponent } from './user-update/user-update.component';
import { UserCreateComponent } from './user-create/user-create.component';
import { AuthGuard } from '../shared/auth-guards/auth-guard/auth.guard';
import { MessageComponent } from './message/message.component';

var routes:Routes = [
    {path: 'users/create',component: UserCreateComponent, canActivate:[AuthGuard]},
    {path: 'users',component: UserListComponent  , canActivate:[AuthGuard] },
    {path: 'users/:id',component: UserByIdComponent  , canActivate:[AuthGuard]},
    {path: 'users/:id/edit',component: UserUpdateComponent , canActivate:[AuthGuard] },
    {path: 'users/:id/message',component: MessageComponent , canActivate:[AuthGuard] },
    {path:'', redirectTo: 'users', pathMatch: 'full'}
]

@NgModule({
    declarations: [
            UserListComponent,
            UserByIdComponent,
            UserUpdateComponent,
            UserCreateComponent,
            MessageComponent
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