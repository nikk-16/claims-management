import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from 'src/material.module';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { SignUpPageComponent } from './components/sign-up-page/sign-up-page.component';
import { HomePageComponent } from './components/home-page/home-page.component';
import { HeaderComponent } from './components/navigation/header/header.component';
import { ApplyForClaimPageComponent } from './components/apply-for-claim-page/apply-for-claim-page.component';
import { InsuranceDetailsPageComponent } from './components/insurance-details-page/insurance-details-page.component';
import { ClaimsDetailsPageComponent } from './components/claims-details-page/claims-details-page.component';
import { ApplyForInsurancePageComponent } from './components/apply-for-insurance-page/apply-for-insurance-page.component';
import { ClaimsInterceptor } from './services/claims.interceptor';
import { SidebarComponent } from './components/navigation/sidebar/sidebar.component';
import { SharedModule } from 'src/shared/shared.module';
import { InsuranceCardComponent } from 'src/shared/shared-components/insurance-card/insurance-card.component';
import { ApplyClaimsCardComponent } from 'src/shared/shared-components/apply-claims-card/apply-claims-card.component';
import { InsuranceDescriptionPageComponent } from 'src/shared/shared-components/insurance-description-page/insurance-description-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ShowInsuranceComponent } from './components/show-insurance/show-insurance.component';


@NgModule({
    declarations: [
        AppComponent,
        LoginPageComponent,
        SignUpPageComponent,
        HomePageComponent,
        HeaderComponent,
        ApplyForClaimPageComponent,
        InsuranceDetailsPageComponent,
        ClaimsDetailsPageComponent,
        ApplyForInsurancePageComponent,
        SidebarComponent,
        InsuranceCardComponent,
        ApplyClaimsCardComponent,
        ProfileComponent,
        InsuranceDescriptionPageComponent
    ],
    providers: [{ provide: HTTP_INTERCEPTORS, useClass: ClaimsInterceptor, multi: true }],
    bootstrap: [AppComponent],
    imports: [
        BrowserModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        MaterialModule,
        FormsModule,
        HttpClientModule,
        SharedModule
    ]
})
export class AppModule { }
