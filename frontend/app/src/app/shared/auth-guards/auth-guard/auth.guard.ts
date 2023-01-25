import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable()
export class AuthGuard implements CanActivate
{
    constructor(private tokenStorage:TokenStorageService, private router:Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
    var isAuthenticated = false;

    this.tokenStorage.getToken();
    this.tokenStorage.isTokenValid$.subscribe(
        result => isAuthenticated = result
    )

     if (!isAuthenticated)
     {
        this.router.navigate(['/..','login']);
     }
       return isAuthenticated;
    }
}