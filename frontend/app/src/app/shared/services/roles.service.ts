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
      catchError(error => throwError(() => this.handleErrorException(error)))
    );
  }

  private handleErrorException(error:HttpErrorResponse): string
  {
    var errorMessage = "";
    switch (error.status)
    {
      case 404:
        errorMessage = "Employee Not found";
        break;
      case 500:
        errorMessage = "Unknown error was thrown";
    }
    return errorMessage;
  }


}
