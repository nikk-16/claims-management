import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { claimsUri } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class ClaimsService {

  constructor(private http:HttpClient) { }
  applyForClaims(claim:any){
    return this.http.post(`${claimsUri}/apply`,claim,{responseType:"json"});
  }
}
