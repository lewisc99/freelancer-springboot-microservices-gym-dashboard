import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { EmployeesDto } from '../domain/dtos/EmployeesDto';
import { environment } from '../../../environments/environment.test';
import { Observable, map } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  private fullUrl = environment.employees_URL;

  constructor(private http:HttpClient) { }

    public  getAll(): Observable<EmployeesDto>
    {
      
     return this.http.get<EmployeesDto>(this.fullUrl).pipe(
      map(
        (response:EmployeesDto) =>
        {
          return response;
        }
      )
     )
    }
}
