import { NgModule } from "@angular/core";
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';


const routes:Routes = [
  {path:"login", component:LoginComponent}

]

@NgModule({
    declarations: [
    LoginComponent
  ],
    imports: [
        RouterModule.forRoot(routes)
    ],
    exports: []
})
export class AuthModule {}