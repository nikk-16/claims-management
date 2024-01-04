import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClaimsService {

  constructor(private http:HttpClient) { }
  private url="http://localhost:8080/claims";
  applyForClaims(claim:any){
    return this.http.post(`${this.url}/applyClaim`,claim,{responseType:"json"});
  }
}
