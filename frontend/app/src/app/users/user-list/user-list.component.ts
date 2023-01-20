import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersDTO } from '../domain/dtos/usersDto';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{


  public usersDTO:UsersDTO = new UsersDTO();

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService){}
  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      params => 
      {
        this.getAll();
      }
    )
  }

  public getAll(sortBy?:string) : void
  {
       
  }

}
