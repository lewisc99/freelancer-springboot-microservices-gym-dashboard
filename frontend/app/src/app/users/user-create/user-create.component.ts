import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';
import { CategoryService } from '../services/category-service/category.service';
import { CategoryDTO } from '../domain/dtos/categoryDTO';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit{

  userGroup: FormGroup;
  categories:CategoryDTO[] = [];
  status:string[] = ["WAITING_PAYMENT","PAID","CANCELED"];

  constructor(private fb: FormBuilder, private categoryService:CategoryService){}

  ngOnInit(): void {
      this.userGroup = this.fb.group({
          user : this.fb.group({
            id: new FormControl(),
            username: new FormControl(),
            email: new FormControl(),
            age: new FormControl(),
            doc: new FormControl(),
          }),
          plan: this.fb.group({
            id: new FormControl(),
            start: new FormControl(),
            finish: new FormControl(),
            status: new FormControl(),
            category: this.fb.group({
              id: new FormControl(),
              name: new FormControl()
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
      console.log(this.userGroup);
  }

}
