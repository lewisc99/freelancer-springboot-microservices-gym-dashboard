import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';
import { Token } from '../../models/token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  private storageToken:Storage = localStorage;
  public isTokenValid:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() { 
    
  }

  public saveToken(token:Token)
  {
    this.storageToken.setItem("token",JSON.stringify(token.token));
    var hasAdminRole = token.roles.findIndex(role => role == "admin" || role == "manager");
    if (hasAdminRole == 0)
    {
        this.storageToken.setItem("isRoleAdmin","true");
    }
    this.isTokenValid.next(true);
  }

  public getToken():string 
  {
    
    let token = JSON.parse( this.storageToken.getItem("token")!);
    if ( token == "")
    {
        this.isTokenValid.next(false);
        return "";
    }
    this.isTokenValid.next(true);
    return token;
  }


  public cleanToken():void
  {
     this.storageToken.removeItem("token");
     this.storageToken.removeItem("isRoleAdmin");
     this.isTokenValid.next(false);
  }

  public hasRoleAdmin()
  {
    let roleAdmin = JSON.parse( this.storageToken.getItem("isRoleAdmin")!);
    if ( roleAdmin == ""  || roleAdmin == null)
    {
        return false;
    }
    console.log(roleAdmin);
    return true;
  }

}
