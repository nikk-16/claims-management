import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AppConstant } from '../util/constants';
@Injectable({
  providedIn: 'root'
})
export class InsurancesService {

  constructor(private http:HttpClient) { }
  // insurances:insurance[]=[{
  //   id:"123456",
  //   username:"456789",
  //   type:"health",
  //   amount:100000,
  //   startDate:new Date('1/1/2003'),
  //   endDate:new Date('1/1/2013'),
  //   maxClaim:400000
  // },
  // {
  //   id:"123457",
  //   username:"456789",
  //   type:"vehicle",
  //   amount:234560,
  //   startDate:new Date('1/1/2003'),
  //   endDate:new Date('1/1/2013'),
  //   maxClaim:567889
  // }]
  getAllInsurancesByUsername(username:string){
    return this.http.get<any>(`${AppConstant.INSURANCE_URI}/user/${username}`);
  }

  applyForInsurance(newInsurance:any){
    return this.http.post(`${AppConstant.INSURANCE_URI}/buy`,newInsurance,{responseType:"json"});
  }

}
