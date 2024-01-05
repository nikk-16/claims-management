import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { insurancePoliciesUri } from '../constants';

@Injectable({
  providedIn: 'root'
})
export class InsurancePoliciesService {

  constructor(private http:HttpClient) { }

  getAllInsurancePolicies(){
    return this.http.get<any>(`${insurancePoliciesUri}/getall`)
  }

  getInsurancePolicyById(id:string){
    return this.http.get<any>(`${insurancePoliciesUri}/${id}`);
  }
}
