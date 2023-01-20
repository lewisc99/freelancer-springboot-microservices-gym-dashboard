import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

@Component({
  selector: 'app-user-create',
  templateUrl: './user-create.component.html',
  styleUrls: ['./user-create.component.css']
})
export class UserCreateComponent implements OnInit{

  userGroup: FormGroup;

  constructor(private fb: FormBuilder){}

  ngOnInit(): void {
      this.userGroup = this.fb.group({
          userModel : this.fb.group({
            id: new FormControl(),
            username: new FormControl(),
            email: new FormControl(),
            age: new FormControl(),
            doc: new FormControl(),
            plan: this.fb.group({
                id: new FormControl(),
                start: new FormControl(),
                finish: new FormControl(),
                category: this.fb.group({
                  id: new FormControl(),
                  name: new FormControl()
                }),
                status: new FormControl()
            })
          })
      })
  }

  onSubmit()
  {
      console.log(this.userGroup);
  }

}
