import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { Message } from '../domain/dtos/message';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  public username:string;

  public listMessages: Message[] = [
    {subject: "Payment Required", text : "Dear Customer, You're Bill is expired Please Pay the Bill soon as possible to continue with the Service"},
    {subject: "Bill Date to expired", text : "Dear Customer, you're Bill is almost expired Please pay the bill before expiration"},
    {subject: "Discount in the Next Bill", text : "Dear Customer, you got a Discount in your next bill to validate the discount please Contact our customer's service, in the number 31991143417"},
    {subject: "You're payment was aknowdge", text : "Dear customer, your payment is recognize in our system, please not consider any further Email"}
  ];

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
