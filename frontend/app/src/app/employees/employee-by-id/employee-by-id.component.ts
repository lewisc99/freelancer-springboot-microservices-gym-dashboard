import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-employee-by-id',
  templateUrl: './employee-by-id.component.html',
  styleUrls: ['./employee-by-id.component.css']
})
export class EmployeeByIdComponent implements OnInit{

  constructor(private route:ActivatedRoute){}

  public username:string ="";

  ngOnInit(): void {

    this.username = this.route.snapshot.params['id'];
    
  }

}
