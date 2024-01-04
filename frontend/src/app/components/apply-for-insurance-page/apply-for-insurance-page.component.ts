import { formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { InsurancesService } from 'src/app/services/insurances.service';

@Component({
  selector: 'app-apply-for-insurance-page',
  templateUrl: './apply-for-insurance-page.component.html',
  styleUrl: './apply-for-insurance-page.component.scss'
})
export class ApplyForInsurancePageComponent {
  form: any;
  username: any;
  today = new Date();
  constructor(private fb: FormBuilder, private insuranceService: InsurancesService) {
    this.username = localStorage.getItem('username');
    this.form = this.fb.group({
      username: [`${this.username}`, Validators.required],
      type: ['', Validators.required],
      amount: ['', Validators.required],
      startDate: [formatDate(`${this.today}`, 'yyyy-MM-dd', 'en') ,Validators.required],
      endDate: ['', Validators.required],
      maxClaim: ['', Validators.required]
    });
  }

  insuranceRequest() {
    console.log(this.form.value);
    this.form.value.endDate = formatDate(`${this.form.value.endDate}`, 'yyyy-MM-dd', 'en');
    console.log(formatDate(`${this.form.value.endDate}`, 'yyyy-MM-dd', 'en'));
    console.error(this.form.value);
    this.insuranceService.applyForInsurance(this.form.value).subscribe(response => {
      console.log(response);
    })
  }
}
