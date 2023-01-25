import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { EmployeeModule } from './employees/employees.module';
import { RouterModule, Routes } from '@angular/router';
import { SharedModule } from './shared/shared.module';
import { UsersModule } from './users/users.module';
import { AuthModule } from './auth/auth.module';
import { NotFoundComponent } from './shared/components/not-found/not-found.component';


const routes:Routes = [
    {path:'not-found', component: NotFoundComponent},
    {path:"**", redirectTo:'not-found',pathMatch:'full'}
]

@NgModule({
    declarations: [
        AppComponent,
    ],
    providers: [],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        EmployeeModule,
        HttpClientModule,
        RouterModule.forRoot(routes),
        SharedModule,
        UsersModule,
        AuthModule,
    ]
})
export class AppModule { }
