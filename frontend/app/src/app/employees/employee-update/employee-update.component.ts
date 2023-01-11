import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';

@Component({
  selector: 'app-employee-update',
  templateUrl: './employee-update.component.html',
  styleUrls: ['./employee-update.component.css']
})
export class EmployeeUpdateComponent implements OnInit{

  constructor(private route:ActivatedRoute) {}

  public id:string;

  ngOnInit(): void {

      this.route.paramMap.subscribe( 
        (params:any) =>
        {
         this.id =  params.get('id');
        }
      )
  }

  

}
