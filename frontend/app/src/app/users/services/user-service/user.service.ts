import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse} from '@angular/common/http';
import { environment } from '../../../../environments/environment.test';
import { Observable, catchError, map, throwError } from 'rxjs';
import { UsersDTO } from '../../domain/dtos/usersDTO';
import { UserDTO } from '../../domain/dtos/userDTO';
import { Message } from '../../domain/dtos/message';
import { Route, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private fullURL = environment.users_URL;

  constructor(private httpClient:HttpClient, private router:Router) { }

  create(user: UserDTO): Observable<any>
  {
      return this.httpClient.post<UserDTO>(this.fullURL, user).pipe(
        map( response => response),
        catchError(error => throwError(() => this.handleErrorException(error)))
      )
  }

  getAll(sortBy?:string) :Observable<UsersDTO>
  {
    let getAllUrl = this.fullURL + "?pagNumber=0&pagSize=5&sortBy=" + sortBy;

    return this.httpClient.get<UsersDTO>(getAllUrl).pipe(
      map(
         (response:UsersDTO) => response
      ), catchError( error => throwError(() => this.handleErrorException(error)))
    )
  }

  getById(id:string) : Observable<UserDTO>
  {
    let getByIdURL = this.fullURL  + "/" + id;
    return this.httpClient.get<UserDTO>(getByIdURL).pipe
    (
      map(
          (response:UserDTO) => response
      ),
      catchError( error => throwError(() => this.handleErrorException(error)))
    )
  }

  update(user:UserDTO) : Observable<any>
  {
    let getBydIdURL = this.fullURL + "/" + user.id;
    return this.httpClient.put<UserDTO>(getBydIdURL, user).pipe
    (
      catchError(error => throwError(() => this.handleErrorException(error)))
    )
  }

  delete(id:string) : Observable<any>
  {
    let getByIdURL = this.fullURL + "/" + id;
    return this.httpClient.delete(getByIdURL).pipe
    (
      catchError(error => throwError(() => this.handleErrorException(error)))
    )
  }
  
  saveMessage(message:Message): Observable<any>
  {
    let URL = this.fullURL + "/" + message.user + "/message";
    return this.httpClient.post<Message>(URL,message).pipe
    (
      catchError(error => throwError(() => this.handleErrorException(error)))
    )

  }

  private handleErrorException(error:HttpErrorResponse): string
  {
    var errorMessage = "";
    switch (error.status)
    {
      case 401:
        errorMessage = "Token Expired, Please LogIn Again";
        this.router.navigate(['/..','login']);
        break;
      case 404:
        errorMessage = "Employee Not found";
        break;
      case 500:
        errorMessage = "Unknown error was thrown";
        break;
    }
    return errorMessage;
  }
}
