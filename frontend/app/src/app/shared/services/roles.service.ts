import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Roles } from '../../employees/domain/entities/roles';
import { Observable, catchError, map, throwError } from 'rxjs';
import { environment } from '../../../environments/environment.test';

@Injectable({
  providedIn: 'root'
})
export class RolesService {

  constructor(private http:HttpClient) { }

  private fullUrl = environment.roles_URL;

  public getAll():Observable<Roles[]>
  {
    return this.http.get(this.fullUrl).pipe(
      map(
        (response:any) => response
      ),
      catchError(this.handleError)
    )
  }

  
  private handleError(errorRes: HttpErrorResponse) {
    let errorMessage = 'An unknown error occurred!';
    if (!errorRes.error || !errorRes.error.error) {
      return throwError(errorMessage);
    }
    switch (errorRes.error.error.message) {
      case 'EMAIL_EXISTS':
        errorMessage = 'This email exists already';
        break;
      case 'EMAIL_NOT_FOUND':
        errorMessage = 'This email does not exist.';
        break;
      case 'INVALID_PASSWORD':
        errorMessage = 'This password is not correct.';
        break;
    }
    return throwError(errorMessage);
  }


}
