import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  public username:string;

  constructor(private activatedRoute:ActivatedRoute, private UserService: UserService) {}

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe({
      next: params => {
       let username =  params.get("username")!;
       this.username = username;
      }
    })
  }

  
    
}
