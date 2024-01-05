import { Component} from '@angular/core';
import { InsurancePolicies } from 'src/app/model/insurance-policies';
import { InsurancePoliciesService } from 'src/app/services/insurance-policies.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent {
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
