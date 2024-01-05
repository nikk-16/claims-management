import { Component, Input } from '@angular/core';
import { InsurancePolicies } from 'src/app/model/insurance-policies';

@Component({
  selector: 'app-insurance-card',
  templateUrl: './insurance-card.component.html',
  styleUrl: './insurance-card.component.scss'
})
export class InsuranceCardComponent {
 @Input()
 insurancePolicy:InsurancePolicies|any;
}
