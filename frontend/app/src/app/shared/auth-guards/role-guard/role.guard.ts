import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { Observable } from "rxjs";
import { TokenStorageService } from '../../../auth/services/token-storage/token-storage.service';

@Injectable()
export class RoleGuard implements CanActivate
{
    constructor(private tokenStorage:TokenStorageService, private router:Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> {
        
       let hasAdminRole = this.tokenStorage.hasRoleAdmin();

       if (!hasAdminRole)
       {
        this.router.navigate(["/..","users"]);
         return false;
       }
       return true;
       }
}