import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Router } from '@angular/router';
import { InsurancePolicies } from 'src/app/model/insurance-policies';

@Component({
  selector: 'app-insurance-card',
  templateUrl: './insurance-card.component.html',
  styleUrl: './insurance-card.component.scss'
})
export class InsuranceCardComponent {
 @Input()
 insurancePolicy:InsurancePolicies|any;
 @Output() policy!: EventEmitter<InsurancePolicies>;

 constructor(private router: Router){}

 toViewInsurance(event: any){
  // this.policy.emit(this.insurancePolicy);
  this.router.navigate(["insurances", this.insurancePolicy.id]);
 }
}
