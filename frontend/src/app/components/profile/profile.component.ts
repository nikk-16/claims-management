import { Component } from '@angular/core';
import { InsurancesService } from 'src/app/services/insurances.service';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  insurances:any=[];
  userDetails:any;
  username:string|any;
  displayedColumns: string[] = ['id', 'username', 'type','amount','startDate','endDate','maxClaim','button'];
  constructor(private insuranceService:InsurancesService, private userService: UsersService){
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
      //console.log(response);
    });
    this.userService.getUserByUsername(this.username).subscribe(response=>{
      this.userDetails=response
     // console.log(response);
    });
   
  }
}
