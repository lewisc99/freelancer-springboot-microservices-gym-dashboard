import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UsersDTO } from '../domain/dtos/usersDTO';
import { UserService } from '../services/user-service/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit{


  public usersDTO:UsersDTO = new UsersDTO();
  public sortBy = "";
  public callModal = false;
  public messageModal = "";
  public userIdToDelete = "";

  constructor(private activatedRoute:ActivatedRoute, private userService:UserService){}
  ngOnInit(): void {

    this.activatedRoute.paramMap.subscribe(
      () => this.getAll()
    )
    
  }

  public getAll(sortBy?:string) : void
  {     
    if (sortBy == null || sortBy == "")
    {
      sortBy = "username";
    }

    this.sortBy = sortBy;
       this.userService.getAll(sortBy).subscribe(
         { 
          next: response => {this.usersDTO = response
          console.log(this.usersDTO)},
          error: error => alert(error)}
       )
  }

  public onDeleteModal(id:string,username:string)
  {
      this.callModal = true;
      this.userIdToDelete = id;
      this.messageModal = "Are you sure you want to Delete " + username + "?"
  }

  public closeModal()
  {
    this.callModal = false;
  }

  onDeleteUser()
  {
    this.closeModal();
    this.messageModal = "";
    
    this.userService.delete(this.userIdToDelete).subscribe({
      next: () => this.getAll(),
      error: error => alert(error)
    })
  }

}
