import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { EmployeeModule } from './employees/employees.module';
import { RouterModule } from '@angular/router';
import { SharedModule } from './shared/shared.module';
import { UsersModule } from './users/users.module';
import { AuthModule } from './auth/auth.module';
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
        RouterModule,
        SharedModule,
        UsersModule,
        AuthModule
    ]
})
export class AppModule { }
