import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable({providedIn:"root"})
export class AuthGuard implements CanActivate
{
    constructor(private tokenStorage:TokenStorageService, private router:Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        
    //    let hasRole = this.tokenStorage.getRoles().filter(role => role == 'manager' || role == 'admin');
        
       
       if (this.tokenStorage.isTokenValid)
       {
        return true;
       }
       else
       {
        this.router.navigate(['/..', 'login'])
        return false;
       }
    }


    
}