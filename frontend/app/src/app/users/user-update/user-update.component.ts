import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserDTO } from '../domain/dtos/userDTO';
import { CategoryService } from '../services/category-service/category.service';
import { CategoryDTO } from '../domain/dtos/categoryDTO';
import { PlanDTO } from '../domain/dtos/planDTO';
import { LewisModulesValidators } from 'src/app/shared/validators/lewis-modules-validators';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit{

  public userGroup:FormGroup;
  public user:UserDTO = new UserDTO();
  public categories: CategoryDTO[] = [];
  status:string[] = ["WAITING_PAYMENT","PAID","CANCELED"];


  constructor(private activatedRoute:ActivatedRoute, private userService: UserService, private fb:FormBuilder,
    private categoryService: CategoryService, private route: Router){}

  ngOnInit(): void {
      this.activatedRoute.paramMap.subscribe({
        next: params => {
          let id = params.get("id")!;
          this.getById(id);
        },
        error: error => console.log(error)
      })
      this.getCategories();
  }

  private getById(id:string) : void
  {
    this.userService.getById(id).subscribe({
      next: result => {
        this.user = result;
        this.addFormsGroup();
      },
      error: error => console.log(error)
    })
  }

  private addFormsGroup()
  {
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
              id: this.fb.control(""),
              name: this.fb.control(""),
            }),
      })
    })
  }

  getCategories()
  {
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

      this.userService.update(userForm).subscribe({
        next: () => this.route.navigate(["..","users"]),
        error: error => console.log(error)
      });
   }
   this.userGroup.markAllAsTouched();
   
   }
  

}
