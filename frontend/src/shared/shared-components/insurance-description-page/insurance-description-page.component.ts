import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { InsurancePolicies } from 'src/app/model/insurance-policies';
import { InsurancePoliciesService } from 'src/app/services/insurance-policies.service';

@Component({
  selector: 'app-insurance-description-page',
  templateUrl: './insurance-description-page.component.html',
  styleUrl: './insurance-description-page.component.scss'
})
export class InsuranceDescriptionPageComponent {
  insuranceId: string|any;
  insurancePolicy:InsurancePolicies|any;
  username :string |any= localStorage.getItem("username");
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
