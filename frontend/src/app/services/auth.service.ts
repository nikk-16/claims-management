import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
 
@Injectable({
  providedIn: 'root',
})
export class AuthGuard {
  constructor(private router:Router){}
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      let username:string|null=localStorage.getItem('username');
      if(username!=null && username.length>0){
        return true;
      }
      else{
        this.router.navigate(['/login']);
        return false;
      }
  }
}