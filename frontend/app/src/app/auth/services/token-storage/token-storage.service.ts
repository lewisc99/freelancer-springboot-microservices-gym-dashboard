import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Token } from '../../models/token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private storageToken:Storage = localStorage;
  public isTokenValid$:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public hasRoleAdmin$:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() { 
    this.hasRoleAdmin();
  }

  public saveToken(token:Token)
  {
    this.storageToken.setItem("token",JSON.stringify(token));
    var hasAdminRole = token.roles.findIndex(role => role == "admin" || role == "manager");
    if (hasAdminRole >= 0)
    {
        this.storageToken.setItem("isRoleAdmin","true");
        this.hasRoleAdmin$.next(true);
    }
    this.isTokenValid$.next(true);
  }

  private autoLogout(token:Token)
  {
    let currentDate = Date.now();
    let expirationDate = Date.parse(token.expirationToken);
    if (currentDate >= expirationDate)
    {
        this.cleanToken();
    }
  }

  public getToken():string 
  {

    let token:Token = JSON.parse( this.storageToken.getItem("token")!);
    if ( token == null)
    {
        console.log(token);
        this.isTokenValid$.next(false);
        return "";
    }
    else
    {
      this.autoLogout(token);
      this.isTokenValid$.next(true);
      return token.token;
    }
  }

  public cleanToken():void
  {
     this.storageToken.removeItem("token");
     this.storageToken.removeItem("isRoleAdmin");
     this.isTokenValid$.next(false);
  }

   public hasRoleAdmin()
   {
     let roleAdmin = JSON.parse( this.storageToken.getItem("isRoleAdmin")!);
     if ( roleAdmin == ""  || roleAdmin == null)
     {
         return false;
     }
     this.hasRoleAdmin$.next(true);
     return true;
   }

}
