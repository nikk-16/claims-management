import { Component, OnInit } from '@angular/core';
import { InsurancePolicies } from 'src/app/model/insurance-policies';
import { InsurancePoliciesService } from '../../services/insurance-policies.service';

@Component({
  selector: 'app-insurance-details-page',
  templateUrl: './insurance-details-page.component.html',
  styleUrl: './insurance-details-page.component.scss'
})
export class InsuranceDetailsPageComponent implements OnInit{

  insurancePolicies:InsurancePolicies[]=[];
  constructor(private insurancePoliciesService:InsurancePoliciesService){

  }
  ngOnInit(){
    this.insurancePoliciesService.getAllInsurancePolicies().subscribe(response=>{
      this.insurancePolicies=response;
    })
  }

}
