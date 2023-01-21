import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CategoryService } from '../services/category-service/category.service';
import { CategoryDTO } from '../domain/dtos/categoryDTO';
import { UserDTO } from '../domain/dtos/userDTO';
import { PlanDTO } from '../domain/dtos/planDTO';
import { UserService } from '../services/user-service/user.service';
import { Router } from '@angular/router';
import { LewisModulesValidators } from '../../shared/validators/lewis-modules-validators';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit{

  userGroup: FormGroup;
  categories:CategoryDTO[] = [];
  status:string[] = ["WAITING_PAYMENT","PAID","CANCELED"];

  constructor(private fb: FormBuilder, private categoryService:CategoryService,private userService:UserService, private route:Router){}

  ngOnInit(): void {
      this.userGroup = this.fb.group({
          user : this.fb.group({
            id: this.fb.control(""),
            username: this.fb.control("",[Validators.required,Validators.minLength(5),
               LewisModulesValidators.notOnlyWhiteSpace,  Validators.maxLength(20)]),

            email:this.fb.control("", [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
            age: this.fb.control("", [Validators.required, Validators.min(18), Validators.max(90)]),
            doc: this.fb.control("", [Validators.required, Validators.minLength(10),Validators.maxLength(20)]),
          }),
          plan: this.fb.group({
            id: this.fb.control(""),
            start: this.fb.control("", [Validators.required]),
            finish: this.fb.control("", [Validators.required]),
            status: this.fb.control("", [Validators.required]),
            category: this.fb.group({
              id: this.fb.control("", [Validators.required]),
              name: this.fb.control(""),
            }),
        })
      })

      this.categoryService.getAll().subscribe(
        {
          next: response => this.categories = response,
          error: error => console.log(error)
        }
      )
  }

  get username() {return this.userGroup.get("user.username")}
  get email() {return this.userGroup.get("user.email")}
  get age() {return this.userGroup.get("user.age")}
  get doc() {return this.userGroup.get("user.doc")}

  get start() {return this.userGroup.get("plan.start")}
  get finish() {return this.userGroup.get("plan.finish")}
  get getStatus() {return this.userGroup.get("plan.status")}

  get categoryId() { return this.userGroup.get("category.id")}
  get categoryName() { return this.userGroup.get("category.name")}



  onSubmit()
  {

    if (!this.userGroup.invalid)
    {
      let userForm: UserDTO = this.userGroup.value.user;
      let plan:PlanDTO = this.userGroup.value.plan;
      let categoryName = this.userGroup.value.plan.category.name;
      let category:CategoryDTO = this.categories.find(category => category.name == categoryName)!;
      
      plan.category = category;
      userForm.plan = plan;
  
      this.userService.create(userForm).subscribe({
        next: () => this.route.navigate(["..","users"]),
        error: error => console.log(error)
      })
     }
       this.userGroup.markAllAsTouched();
    }

   

}
