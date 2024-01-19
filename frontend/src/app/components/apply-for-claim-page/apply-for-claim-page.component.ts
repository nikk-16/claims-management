import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClaimsService } from '../../services/claims.service';
import { InsurancesService } from '../../services/insurances.service';

@Component({
  selector: 'app-apply-for-claim-page',
  templateUrl: './apply-for-claim-page.component.html',
  styleUrl: './apply-for-claim-page.component.scss'
})
export class ApplyForClaimPageComponent {
  form: FormGroup;
  insuranceDetails: any[] = [];
  username: string | null;
  constructor(private fb: FormBuilder, private claimService: ClaimsService, 
    private insuranceServices: InsurancesService,
    public snackBar:MatSnackBar) {
    this.username = localStorage.getItem('username');
    this.form = this.fb.group({
      policyNo: ['', Validators.required],
      name: [`${this?.username}`, Validators.required],
      insuranceType: [''],
      claimReason: ['', Validators.required],
      estimatedAmount: ['', Validators.required]
    });

  }
  ngOnInit() {
    if (this.username != null && this.username!=undefined) {
      this.insuranceServices.getAllInsurancesByUsername(this.username).subscribe(response => {
        this.insuranceDetails = response;
      })
    }
  }

  claimRequest() {
    var policyNo: string = this.form.value.policyNo;
    var selectedInsurance= this.insuranceDetails.filter(obj => obj.id == policyNo);
    this.form.value.insuranceType = selectedInsurance[0].type;
    this.claimService.applyForClaims(this.form.value).subscribe(response => { })
    this.openSnackBar();
  }

  openSnackBar() {
    this.snackBar.open('Claim Submitted','Ok', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration:1000
    });
  }
}
