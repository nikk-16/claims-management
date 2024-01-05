import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { ApplyForClaimPageComponent } from './components/apply-for-claim-page/apply-for-claim-page.component';
import { ApplyForInsurancePageComponent } from './components/apply-for-insurance-page/apply-for-insurance-page.component';
import { InsuranceDetailsPageComponent } from './components/insurance-details-page/insurance-details-page.component';
import { ClaimsDetailsPageComponent } from './components/claims-details-page/claims-details-page.component';
import { authGuard } from './router-guards/auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full',
  },
  {
    path:'signup',
    component:SignUpPageComponent
  },
  {
    path:'login',
    component:LoginPageComponent
  },
  {
    path:'home',
    component:HomePageComponent
  },
  {
    path:'claimApplication',
    component:ApplyForClaimPageComponent,
    canActivate:[authGuard]
  },
  {
    path:'insuranceApplication',
    component:ApplyForInsurancePageComponent,
    canActivate:[authGuard]
  },
  {
    path:'insurances',
    component:InsuranceDetailsPageComponent,
    canActivate:[authGuard]
  },
  {
    path:'claims',
    component:ClaimsDetailsPageComponent,
    canActivate:[authGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
