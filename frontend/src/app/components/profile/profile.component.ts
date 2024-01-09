import { Component } from '@angular/core';
import { InsurancesService } from '../../services/insurances.service';
import { UsersService } from '../../services/users.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent {
  insurances:any=[];
  userDetails:any;
  username:string|any='';
  displayedColumns: string[] = ['id', 'username', 'type','amount','startDate','endDate','maxClaim','button'];
  constructor(private insuranceService:InsurancesService, private userService: UsersService){
   if(localStorage.getItem('username')!=undefined && localStorage.getItem('username')!=null){
    this.username=localStorage.getItem('username');
   }
    // this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
      //   this.insurances=response
      //   console.log(response);
    // });
  
  }
  ngOnInit(){
    // this.insurances=this.insuranceService.insurances;
    if(this.username!='' && this.username!=null && this.username!=undefined){
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
}
