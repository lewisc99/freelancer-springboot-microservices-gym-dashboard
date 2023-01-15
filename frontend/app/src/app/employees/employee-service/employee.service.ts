import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { environment } from '../../../environments/environment.test';
import { Observable, catchError, map, throwError } from 'rxjs';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';
import { EmployeeModel } from '../domain/models/employee.model';
import { Router } from '@angular/router';
import { Employee } from '../domain/entities/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private fullUrl = environment.employees_URL;

  constructor(private http:HttpClient, private route:Router) { }



    public create(employee:Employee): Observable<any>
    {
      return this.http.post<Employee>(this.fullUrl,employee)
      .pipe(
        map(
          (response:any) => 
          {}
        ),catchError(this.handleError)
      )
    }

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

    public getById(id:string): Observable<EmployeeDto>
    {
        var getByIdUrl = this.fullUrl + "/" + id;
        return this.http.get<EmployeeDto>(getByIdUrl).pipe(
          map(
            (response:EmployeeDto) =>
            {
              return response;
            },
            catchError(this.handleError)
          )
        );
    }

    public updateEmployee(employee:EmployeeModel): Observable<any>
    {
        var getByIdUrl = this.fullUrl + "/" + employee.id;

        return this.http.put(getByIdUrl, employee).pipe(
          map(
            (response:any) =>
            {
              this.route.navigate(['..'])
              return response;
            },
            catchError(this.handleError)
          )
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
