import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstant } from '../util/constants';

@Injectable({
  providedIn: 'root'
})
export class InsurancePoliciesService {

  constructor(private http:HttpClient) { }

  getAllInsurancePolicies(){
    return this.http.get<any>(`${AppConstant.INSURANCE_POLICIES_URI}/getall`)
  }

  getInsurancePolicyById(id:string){
    return this.http.get<any>(`${AppConstant.INSURANCE_POLICIES_URI}/${id}`);
  }
}
