import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable()
export class RoleGuard implements CanActivate
{
    constructor(private tokenStorage:TokenStorageService, private router:Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        
       var hasAdminRole = this.tokenStorage.getRoles().findIndex(role => role == "admin" || role == "manager");

       if (hasAdminRole < 0)
       {
        this.router.navigate(["/..","users"]);
         return false;
       }
       return true;
       }
}