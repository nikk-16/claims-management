import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InsurancePolicies } from 'src/app/model/insurance-policies';
import { InsurancePoliciesService } from 'src/app/services/insurance-policies.service';
import { InsurancesService } from 'src/app/services/insurances.service';

@Component({
  selector: 'app-show-insurance',
  templateUrl: './show-insurance.component.html',
  styleUrl: './show-insurance.component.scss'
})
export class ShowInsuranceComponent {
  // @Input() insuranceType!: string;
  insuranceId!: string;
  insurancePolicy!:InsurancePolicies;
  username = localStorage.getItem("username")!;
  
  constructor(private ar: ActivatedRoute, private insurancePoliciesService:InsurancePoliciesService){
    this.insuranceId = ar.snapshot.params['id'];
  }
  ngOnInit(){
    this.insurancePoliciesService.getInsurancePolicyById(this.insuranceId).subscribe(response => {
      console.log(response)
      this.insurancePolicy = response;
    })
  }
}