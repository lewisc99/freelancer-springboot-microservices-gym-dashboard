import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../../environments/environment.test';
import { Observable, catchError, map, throwError } from 'rxjs';
import { UsersDTO } from '../../domain/dtos/usersDTO';
import { UserDTO } from '../../domain/dtos/userDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private fullURL = environment.users_URL;

  constructor(private httpClient:HttpClient) { }

  getAll(sortBy?:string) :Observable<UsersDTO>
  {
    let getAllUrl = this.fullURL + "?pagNumber=0&pagSize=5&sortBy=" + sortBy;

    return this.httpClient.get<UsersDTO>(getAllUrl).pipe(
      map(
         (response:UsersDTO) => response
      ), catchError( error => throwError(() => error))
    )
  }

  getById(id:string) : Observable<UserDTO>
  {
    let getByIdURL = this.fullURL  + "/" + id;
    return this.httpClient.get<UserDTO>(this.fullURL).pipe
    (
      map(
          (response:UserDTO) => response
      ),
      catchError( error => throwError(() => error))
    )
  }
}
