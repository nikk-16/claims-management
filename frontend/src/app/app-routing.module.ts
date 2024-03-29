import { NgModule, inject } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ApplyForClaimPageComponent } from './components/apply-for-claim-page/apply-for-claim-page.component';
import { ApplyForInsurancePageComponent } from './components/apply-for-insurance-page/apply-for-insurance-page.component';
import { InsuranceDetailsPageComponent } from './components/insurance-details-page/insurance-details-page.component';
import { ClaimsDetailsPageComponent } from './components/claims-details-page/claims-details-page.component';
import { AuthGuard } from './services/auth.service';
import { ProfileComponent } from './components/profile/profile.component';
import { InsuranceDescriptionPageComponent } from 'src/shared/shared-components/insurance-description-page/insurance-description-page.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path: 'signup',
    component: SignUpPageComponent
  },
  {
    path: 'login',
    component: LoginPageComponent
  },
  {
    path: 'home',
    component: HomePageComponent
  },
  {
    path: 'claimApplication',
    component: ApplyForClaimPageComponent,
    canActivate: [AuthGuard]

  },
  {
    path: 'insuranceApplication',
    component: ApplyForInsurancePageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'insurances',
    component: InsuranceDetailsPageComponent,
    canActivate: [AuthGuard],
    // children: [{ path: ':type', component: ShowInsuranceComponent }]
  },
  {
    path: 'claims',
    component: ClaimsDetailsPageComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'profile',
    component: ProfileComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'insurances/:id',
    component: InsuranceDescriptionPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
