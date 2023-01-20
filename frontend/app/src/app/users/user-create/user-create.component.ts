import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { CategoryService } from '../services/category-service/category.service';
import { CategoryDTO } from '../domain/dtos/categoryDTO';
import { UserDTO } from '../domain/dtos/userDTO';
import { PlanDTO } from '../domain/dtos/planDTO';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit{

  userGroup: FormGroup;
  categories:CategoryDTO[] = [];
  status:string[] = ["WAITING_PAYMENT","PAID","CANCELED"];

  constructor(private fb: FormBuilder, private categoryService:CategoryService,){}

  ngOnInit(): void {
      this.userGroup = this.fb.group({
          user : this.fb.group({
            id: this.fb.control([]),
            username: this.fb.control([]),
            email:this.fb.control([]),
            age: this.fb.control([]),
            doc: this.fb.control([]),
          }),
          plan: this.fb.group({
            id: this.fb.control([]),
            start: this.fb.control([]),
            finish: this.fb.control([]),
            status: this.fb.control([]),
            category: this.fb.group({
              id: this.fb.control([]),
              name: this.fb.control([]),
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

  onSubmit()
  {
    let userForm: UserDTO = this.userGroup.value.user;
    let plan:PlanDTO = this.userGroup.value.plan;
    let categoryName = this.userGroup.value.plan.category.name;
    let category:CategoryDTO = this.categories.find(category => category.name == categoryName)!;
    
    plan.category = category;
    userForm.plan = plan;

  }

}
