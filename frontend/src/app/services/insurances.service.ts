import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstant } from '../util/constants';
@Injectable({
  providedIn: 'root'
})
export class InsurancesService {

  constructor(private http:HttpClient) { }
  getAllInsurancesByUsername(username:string){
    return this.http.get<any>(`${AppConstant.INSURANCE_URI}/${username}`);
  }

  applyForInsurance(newInsurance:any){
    return this.http.post(`${AppConstant.INSURANCE_URI}/buy`,newInsurance,{responseType:"json"});
  }

}
