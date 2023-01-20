import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { UserDTO } from '../domain/dtos/userDTO';

@Component({
  selector: 'app-user-by-id',
  templateUrl: './user-by-id.component.html',
  styleUrls: ['./user-by-id.component.css']
})
export class UserByIdComponent implements OnInit{

  public user:UserDTO = new UserDTO();

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService){}

  ngOnInit(): void {
      this.activatedRoute.paramMap.subscribe(
        {
          next: param => {
            let id =  param.get("id")!;
            this.getById(id);
          },
          error: error => console.log(error)
        }
      )
  }

  getById(id:string)
  {
    this.userService.getById(id).subscribe({
      next: (result: UserDTO) =>
      {
        this.user = result;
        console.log(this.user);
      },
      error: error => console.log(error)
    });
  }

}
