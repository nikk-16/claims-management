import { inject } from "@angular/core"
import { Router } from "@angular/router";

export const canActivate=()=>{
    const router=inject(Router)
    const username:string|null =localStorage.getItem('username');
    if(username!=""){
        return true;
    }
    else{
        router.navigate(['/login'])
        return false;
    }
}

export const canActivateChild=()=>{
    return canActivate();
}