import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { environment } from '../../../environments/environment.test';
import { Observable, catchError, map, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private fullUrl = environment.employees_URL;

  constructor(private http:HttpClient) { }

    public getAll(): Observable<EmployeesDto>
    {
      
     return this.http.get<EmployeesDto>(this.fullUrl).pipe(
      map(
        (response:EmployeesDto) =>
        {
          return response;
        }
      ),
      catchError (
        this.handleError)
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
