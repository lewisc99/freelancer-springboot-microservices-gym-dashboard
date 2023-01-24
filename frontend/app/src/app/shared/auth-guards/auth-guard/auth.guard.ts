import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable()
export class AuthGuard implements CanActivate
{
    constructor(private tokenStorage:TokenStorageService, private router:Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
    
     let result = false;
     this.tokenStorage.isTokenValid.subscribe({
        next: result => {
            if (result)
            {
                result = true;
            }
            else
            {
                this.router.navigate(['/..','login']);
               result =  false;
            }
        }
       })

       return result;
    }
}