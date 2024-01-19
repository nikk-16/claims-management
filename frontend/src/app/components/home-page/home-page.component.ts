import { Component} from '@angular/core';
import { InsurancePolicies } from '../../model/insurance-policies';
import { InsurancePoliciesService } from '../../services/insurance-policies.service';

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

  //..example for testing global handler 
  // ngOnInit() {
  //   setTimeout(() => {
  //     this.causeRuntimeError();
  //   }, 1000);
  // }
  
  // causeRuntimeError() {
  //   const object = JSON.parse('{ bad json'); // This would throw a SyntaxError
  //}
}
