import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../../models/login';
import { Observable, map, catchError, throwError } from 'rxjs';
import { environment } from '../../../../environments/environment.test';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient) { }

  private fullUrl = environment.login_URL;

  public login(login:Login): Observable<any>
  {
      return this.http.post<Login>(this.fullUrl, login).pipe
      (
          map(
            response => response
          ),catchError(error => throwError(() => error) )
      )
  }
}
