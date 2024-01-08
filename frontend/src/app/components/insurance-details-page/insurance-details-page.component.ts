import { Component, OnInit } from '@angular/core';
import { InsurancePolicies } from 'src/app/model/insurance-policies';
import { InsurancePoliciesService } from 'src/app/services/insurance-policies.service';
import { InsurancesService } from 'src/app/services/insurances.service';


@Component({
  selector: 'app-insurance-details-page',
  templateUrl: './insurance-details-page.component.html',
  styleUrl: './insurance-details-page.component.scss'
})
export class InsuranceDetailsPageComponent implements OnInit{
//   insurances:any=[];
//   username:string|any;
//   displayedColumns: string[] = ['id', 'username', 'type','amount','startDate','endDate','maxClaim'];
//  constructor(private insuranceService:InsurancesService){
//     this.username=localStorage.getItem('username');
//     // this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
//       //   this.insurances=response
//       //   console.log(response);
//     // });
  
//   }
//   ngOnInit(){
//     // this.insurances=this.insuranceService.insurances;
//     this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
//       this.insurances=response
//       console.log(response);
//     });
   
   
//   }
  insurancePolicies:InsurancePolicies[]=[];
  constructor(private insurancePoliciesService:InsurancePoliciesService){

  }
  ngOnInit(){
    this.insurancePoliciesService.getAllInsurancePolicies().subscribe(response=>{
      //console.log(response);
      this.insurancePolicies=response;
    })
  }

}
