import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Login } from '../../models/login';
import { Observable, map, catchError, throwError } from 'rxjs';
import { environment } from '../../../../environments/environment.test';
import { Token } from '../../models/token';
import { TokenStorageService } from '../token-storage/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http:HttpClient, private tokenStorage:TokenStorageService) { }

  private fullUrl = environment.login_URL;

  public login(login:Login): Observable<Token>
  {
      return this.http.post<Token>(this.fullUrl, login).pipe
      (
          map(
            response => {
              this.tokenStorage.saveToken(response.token);
              return response
            }
          ),catchError(error => throwError(() => error) )
      )
  }
}
