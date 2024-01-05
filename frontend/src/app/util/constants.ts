// export const usersUri = "http://localhost:8080/users";
// export const insuranceUri = "http://localhost:8080/insurance";
// export const claimsUri = "http://localhost:8080/claims";


import { Injectable } from '@angular/core';
 
@Injectable({
    providedIn: 'root'
})
export class AppConstant {
    // public static readonly SYSTEM_IDLE_TIMEOUT:number = 900000;
    public static readonly USERS_URI = "http://localhost:8080/users";
    public static readonly INSURANCE_URI = "http://localhost:8080/insurance";
    public static readonly CLAIMS_URI = "http://localhost:8080/claims";
    // public static readonly SEARCH_RESULT_NOT_FOUND = 'No records found for the search criteria entered.';
    // public static readonly EVENT_NOT_FOUND = 'No Events Found for the Claim ';
    // public static readonly DEFAULT_ERROR= 'Error from merlin backend';
   
    private constructor() { }
   
}