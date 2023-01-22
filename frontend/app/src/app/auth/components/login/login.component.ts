import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { UserService } from 'src/app/users/services/user-service/user.service';
import { Login } from '../../models/login';
import { LewisModulesValidators } from '../../../shared/validators/lewis-modules-validators';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit, OnDestroy{

  
  loginForm:FormGroup;
  login:Login;
  errorActived:boolean = false;
  errorMessage:string = "";
  loginSubscription:Subscription;

  constructor(private formBuilder:FormBuilder, private userService: UserService , private router:Router) { }
 

  ngOnInit(): void {
    this.loginForm = this.formBuilder.group(
      {
        login: this.formBuilder.group({
          email: new FormControl("",[Validators.required,Validators.minLength(7),Validators.maxLength(40), Validators.email, LewisModulesValidators.notOnlyWhiteSpace]),
          password: new FormControl("",[Validators.required,Validators.minLength(5),Validators.maxLength(30), LewisModulesValidators.notOnlyWhiteSpace]),
        })
      });
      this.loginSubscription = new Subscription();
  }
  
  ngOnDestroy(): void {
    this.loginSubscription.unsubscribe();
  }
  
 get email()
  {
    return this.loginForm.get("login.email");
  }
 get password()
  {
    return this.loginForm.get("login.password");
  }

 public onSubmit()
  {
    this.errorActived = false;
    this.errorMessage = "";

    if (this.loginForm.invalid)
    {
      this.loginForm.markAllAsTouched();
    }
    else
    {
      const email = this.loginForm.get("login")?.get("email")?.value;
      const password = this.loginForm.get("login")?.get("password")?.value;
      this.login = new Login(email,password);
       
       console.log(this.login);
  }
}

}
