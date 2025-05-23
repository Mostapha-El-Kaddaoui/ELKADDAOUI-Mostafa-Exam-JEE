import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from "@angular/router";
import { AuthService } from "../services/auth.service";
import { Observable } from "rxjs";
import { Injectable } from "@angular/core";


@Injectable({
  providedIn: 'root' // This is the recommended way for guards
})
export class AuthorizationGuard implements CanActivate{
  constructor(private authService:AuthService, private router:Router){

  }
  
  canActivate(
    route: ActivatedRouteSnapshot, 
    state: RouterStateSnapshot):Observable<boolean|UrlTree> | Promise<boolean|UrlTree> | boolean | UrlTree{
    if(this.authService.roles.includes("ROLE_EMPLOYE")){
      return true;
    }else{
      this.router.navigateByUrl("/admin/notauthorized")
      return false;
    }
  }
}