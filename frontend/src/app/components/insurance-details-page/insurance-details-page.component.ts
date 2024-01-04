import { Component, OnInit } from '@angular/core';
import { InsurancesService } from 'src/app/services/insurances.service';


@Component({
  selector: 'app-insurance-details-page',
  templateUrl: './insurance-details-page.component.html',
  styleUrl: './insurance-details-page.component.scss'
})
export class InsuranceDetailsPageComponent implements OnInit{
  insurances:any=[];
  username:string|any;
  displayedColumns: string[] = ['id', 'username', 'type','amount','startDate','endDate','maxClaim'];
 constructor(private insuranceService:InsurancesService){
    this.username=localStorage.getItem('username');
    // this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
      //   this.insurances=response
      //   console.log(response);
    // });
  
  }
  ngOnInit(){
    // this.insurances=this.insuranceService.insurances;
    this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
      this.insurances=response
      console.log(response);
    });
   
   
  }

}
