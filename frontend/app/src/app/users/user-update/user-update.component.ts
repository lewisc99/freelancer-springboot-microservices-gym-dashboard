import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UserDTO } from '../domain/dtos/userDTO';
import { CategoryService } from '../services/category-service/category.service';
import { CategoryDTO } from '../domain/dtos/categoryDTO';
import { PlanDTO } from '../domain/dtos/planDTO';

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
        id: this.fb.control(this.user.id),
        username: this.fb.control(this.user.username),
        email:this.fb.control(this.user.email),
        age: this.fb.control(this.user.age),
        doc: this.fb.control(this.user.doc),
      }),
      plan: this.fb.group({
        id: this.fb.control(this.user.plan.id),
        start: this.fb.control(this.user.plan.start),
        finish: this.fb.control(this.user.plan.finish),
        status: this.fb.control(this.user.plan.status),
        category: this.fb.group({
          id: this.fb.control(this.user.plan.category.id),
          name: this.fb.control(this.user.plan.category.name),
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

  onSubmit()
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
  

}
