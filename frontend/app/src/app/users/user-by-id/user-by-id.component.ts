import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-user-by-id',
  templateUrl: './user-by-id.component.html',
  styleUrls: ['./user-by-id.component.css']
})
export class UserByIdComponent implements OnInit{

  public id:string = "";

  constructor(private activatedRoute:ActivatedRoute, userService:UserService){}

  ngOnInit(): void {
      this.activatedRoute.paramMap.subscribe(
        {
          next: param => {
            this.id = param.get("id")!

          }
        }
      )
  }

}
