import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../../models/login';
import { Observable, map, catchError, throwError } from 'rxjs';
import { environment } from '../../../../environments/environment.test';
import { Token } from '../../models/token';
import { TokenStorageService } from '../token-storage/token-storage.service';
import { RolesService } from '../../../shared/services/roles.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient, private tokenStorage:TokenStorageService, private RolesService: RolesService) { }

  private fullUrl = environment.login_URL;

  public login(login:Login): Observable<Token>
  {
     let loginURL = this.fullUrl + "login"
      return this.http.post<Token>(loginURL, login).pipe
      (
          map(
            response => {
              this.tokenStorage.saveToken(response);
              return response;
            }
          ),catchError(error => throwError(() => error) )
      )
  }

  public logout(): Observable<any>
  {
    let logoutURL = this.fullUrl + "logout";
    return this.http.post(logoutURL,{}).pipe(
      
      catchError(error => throwError(() => error))
    )
  }


}
