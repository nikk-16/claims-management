import { inject } from "@angular/core"
import { Router } from "@angular/router";
import { UsersService } from "./services/users.service";

export const canActivate=()=>{
    const userService=inject(UsersService);
    const router=inject(Router)
    if(userService.isLoggedin==true){
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