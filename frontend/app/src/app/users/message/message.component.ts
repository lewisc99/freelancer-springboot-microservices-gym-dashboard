import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  public username:string;

  constructor(private activatedRoute:ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe({
      next: params => {
       let username =  params.get("username")!;
       this.username = username;
      }
    })
  }
    
}
