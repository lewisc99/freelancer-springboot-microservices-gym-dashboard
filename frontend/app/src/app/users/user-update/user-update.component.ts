import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit{

  public id:string;
  constructor(private activatedRoute:ActivatedRoute){}

  ngOnInit(): void {
      this.activatedRoute.paramMap.subscribe({
        next: params => this.id = params.get("id")!,
        error: error => console.log(error)
      })
  }

}
