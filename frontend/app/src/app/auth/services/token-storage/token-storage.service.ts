import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Token } from '../../models/token';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  public roles:string[] = [];
  private storageToken:Storage = localStorage;

  constructor() { 
    this.isTokenValid.next(false);
  }
  public isTokenValid:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  public saveToken(token:Token)
  {
    this.storageToken.removeItem("token");
    this.storageToken.setItem("token",JSON.stringify(token.token));
    this.roles = token.roles;
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
     this.isTokenValid.next(false);
      
  }

  public getRoles(): string[]
  {
    return this.roles;
  }
}
