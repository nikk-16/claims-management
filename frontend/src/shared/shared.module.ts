import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { InsuranceCardComponent } from './shared-components/insurance-card/insurance-card.component';
import { ApplyClaimsCardComponent } from './shared-components/apply-claims-card/apply-claims-card.component';
import { InsuranceDescriptionPageComponent } from './shared-components/insurance-description-page/insurance-description-page.component';
import { MaterialModule } from 'src/material.module';



@NgModule({
  declarations: [
  ],
  imports: [
    CommonModule,
    MaterialModule
  ]
})
export class SharedModule { }
