
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
interface insurance{
  id:string,
  username:string,
  type:string,
  amount:number,
  startDate:Date,
  endDate:Date,
  maxClaim:number
}
@Injectable({
  providedIn: 'root'
})
export class InsurancesService {

  constructor() { }
  insurances:insurance[]=[{
    id:"123456",
    username:"456789",
    type:"health",
    amount:100000,
    startDate:new Date('1/1/2003'),
    endDate:new Date('1/1/2013'),
    maxClaim:400000
  },
  {
    id:"123457",
    username:"456789",
    type:"vehicle",
    amount:234560,
    startDate:new Date('1/1/2003'),
    endDate:new Date('1/1/2013'),
    maxClaim:567889

  }]
  getAllInsurancesByUsername(username:string){
    return this.http.get<any>(`${this.url}/getInsurancesByUsername/${username}`);
  }

  applyForInsurance(newInsurance:any){
    return this.http.post(`${this.url}/buy_insurance`,newInsurance,{responseType:"json"});
  }

}
