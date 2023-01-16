import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { environment } from '../../../environments/environment.test';
import { Observable, catchError, map, throwError } from 'rxjs';
import { EmployeeDto } from '../domain/dtos/EmployeeDto';
import { EmployeeModel } from '../domain/models/employee.model';
import { Employee } from '../domain/entities/employee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private fullUrl = environment.employees_URL;

  constructor(private http:HttpClient) { }

    public create(employee:Employee): Observable<any>
    {
      return this.http.post<Employee>(this.fullUrl,employee);
    }

    public getAll(sortBy?:string): Observable<EmployeesDto>
    {
      var getAllURL = this.fullUrl;
      if (sortBy != null)
      {
        getAllURL  += "?sortBy=" + sortBy;
      }
      return this.http.get<EmployeesDto>(getAllURL).pipe(
      map(
        (response:EmployeesDto) =>  response
        )
     )}

    public getById(id:string): Observable<EmployeeDto>
    {
        var getByIdUrl = this.fullUrl + "/" + id;
        return this.http.get<EmployeeDto>(getByIdUrl).pipe(
          map(
            (response:EmployeeDto) => response
          ));
    }

    public updateEmployee(employee:EmployeeModel): Observable<any>
    {
        var getByIdUrl = this.fullUrl + "/" + employee.id;

        return this.http.put(getByIdUrl, employee);
    }

    public delete(id:string): Observable<any>
    {
        var getByIdUrl = this.fullUrl  + "/" + id;
        return this.http.delete(getByIdUrl);
    }
    
}
