import { Component } from '@angular/core';
import { InsurancesService } from 'src/app/services/insurances.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
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
