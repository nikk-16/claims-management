import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstant } from '../util/constants';

@Injectable({
  providedIn: 'root'
})
export class ClaimsService {

  constructor(private http:HttpClient) { }
  applyForClaims(claim:any){
    return this.http.post(`${AppConstant.CLAIMS_URI}/apply`,claim,{responseType:"json"});
  }
}
