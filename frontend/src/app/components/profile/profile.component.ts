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
    this.username=localStorage.getItem('username');
  }
  ngOnInit(){
    // this.insurances=this.insuranceService.insurances;
    if(  this.username!=null && this.username!=undefined){
      this.insuranceService.getAllInsurancesByUsername(this.username).subscribe(response=>{
        this.insurances=response
      });
      this.userService.getUserByUsername(this.username).subscribe(response=>{
        this.userDetails=response
      });
    }
   
  }
}
