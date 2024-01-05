import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

// https://angular.io/guide/router-tutorial-toh#canactivate-requiring-authentication
export const authGuard: CanActivateFn = (route, state) => {
  const router=inject(Router);
  const username:string|null=localStorage.getItem('username');
  if(username!=null && username!.length>0 ){
    return true;
  }
  else{
    router.navigate(['/login']);
    return false;
  }
};
