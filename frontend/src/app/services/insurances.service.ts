
import { Injectable } from '@angular/core';
interface insurance{
  id:string,
  userid:string,
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
    userid:"456789",
    type:"health",
    amount:100000,
    startDate:new Date('1/1/2003'),
    endDate:new Date('1/1/2013'),
    maxClaim:400000
  },
  {
    id:"123457",
    userid:"456789",
    type:"vehicle",
    amount:234560,
    startDate:new Date('1/1/2003'),
    endDate:new Date('1/1/2013'),
    maxClaim:567889

  }]
}
