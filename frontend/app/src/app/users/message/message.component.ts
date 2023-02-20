import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user-service/user.service';
import { Message } from '../domain/dtos/message';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-message',
  templateUrl: './message.component.html',
  styleUrls: ['./message.component.css']
})
export class MessageComponent implements OnInit {

  public username:string;
  public predefinedIsAvailable = false;
  public formGroup:FormGroup;
  @ViewChild('predefinedID') predefinedId: ElementRef;
  public listMessages: Message[] = [
    {id:1,subject: "Payment Required", text : "Dear Customer, You're Bill is expired Please Pay the Bill soon as possible to continue with the Service"},
    {id:2,subject: "Bill Date to expired", text : "Dear Customer, you're Bill is almost expired Please pay the bill before expiration"},
    {id:3,subject: "Discount in the Next Bill", text : "Dear Customer, you got a Discount in your next bill to validate the discount please Contact our customer's service, in the number 31991143417"},
    {id:4,subject: "You're payment was aknowdge", text : "Dear customer, your payment is recognize in our system, please not consider any further Email"}
  ];



  constructor(private activatedRoute:ActivatedRoute, private UserService: UserService, private fb:FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.queryParamMap.subscribe({
      next: params => {
       let username =  params.get("username")!;
       this.username = username;
      }
    });
    this.formGroup = this.fb.group({
        message: this.fb.group({
          subject: this.fb.control("",[Validators.required, Validators.minLength(5),Validators.maxLength(50)]),
          text: this.fb.control("",[Validators.required, Validators.minLength(20),Validators.maxLength(200)])
        })
    })
  }
    
  get subject() {return this.formGroup.get("message.subject")}
  get text() {return this.formGroup.get("message.text")}
    
    onChangeCheckbox()
    {
        console.log("change");
        this.predefinedIsAvailable = !this.predefinedIsAvailable;
    }

    onSubmit(isPredefined:boolean)
    {
      if (isPredefined)
      {
        console.log(this.predefinedId.nativeElement.value);
        return;
      }
      if (this.formGroup.invalid)
      {
        this.formGroup.markAllAsTouched()
        return;
      }
      console.log(this.formGroup);
    }
}
