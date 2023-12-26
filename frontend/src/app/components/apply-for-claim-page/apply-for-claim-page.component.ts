import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-apply-for-claim-page',
  templateUrl: './apply-for-claim-page.component.html',
  styleUrl: './apply-for-claim-page.component.scss'
})
export class ApplyForClaimPageComponent {
  form:any;
  constructor(private fb:FormBuilder){
    this.form=this.fb.group({
      policyNo:['',Validators.required],
      name:['',Validators.required],
      insuranceType:['',Validators.required],
      claimReason:['',Validators.required],
      estimatedAmount:['',Validators.required]
    });
  }

  claimRequest(){
    console.log(this.form.value);
  }
}
