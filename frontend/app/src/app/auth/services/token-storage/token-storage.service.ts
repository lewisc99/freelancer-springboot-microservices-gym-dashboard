import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
  constructor() { 
    this.isTokenValid.next(false);
    

  }
  public isTokenValid:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  private storageToken:Storage = localStorage;

  public saveToken(token:string)
  {
    this.storageToken.removeItem("token");
    this.storageToken.setItem("token",JSON.stringify(token));
    this.isTokenValid.next(true);
  }

  public getToken():string 
  {
    let getObject  = JSON.parse( this.storageToken.getItem("token")!);

    let token = getObject['token'];
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

}
