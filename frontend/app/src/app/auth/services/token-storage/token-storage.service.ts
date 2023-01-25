import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Token } from '../../models/token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  public storageRoles:Storage = localStorage;
  private storageToken:Storage = localStorage;
  public isTokenValid:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  public isAdminRoleValid:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);


  constructor() { 
    this.isTokenValid.next(false);
  }

  public saveToken(token:Token)
  {
    this.storageToken.removeItem("token");
    this.storageToken.removeItem("roles");
    this.storageToken.setItem("token",JSON.stringify(token.token));
    this.storageRoles.setItem("roles",  JSON.stringify(token.roles));
    this.isTokenValid.next(true);
  }

  public getToken():string 
  {
    
    let token = JSON.parse( this.storageToken.getItem("token")!);
    if ( token == "")
    {
        return "";
    }
    this.isTokenValid.next(true);
    return token;
  }


  public cleanToken():void
  {
     this.storageToken.removeItem("token");
     this.storageRoles.removeItem("roles");
     this.isTokenValid.next(false);
     this.isAdminRoleValid.next(false);
  }

  public getRoles(): string[]
  {
    let roles = JSON.parse( this.storageToken.getItem("roles")!);
    if ( roles == "")
    {
        return [];
    }
    return roles;
  }

}
