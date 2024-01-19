import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClaimsService } from '../../services/claims.service';
import { InsurancesService } from '../../services/insurances.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ApplyForClaimPageComponent } from './apply-for-claim-page.component';
import { of } from 'rxjs';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { Overlay } from '@angular/cdk/overlay';
import { FormGroup } from '@angular/forms';

describe('ApplyForClaimPageComponent', () => {
  let component: ApplyForClaimPageComponent;
  let fixture: ComponentFixture<ApplyForClaimPageComponent>;
  let insuranceServiceMock: any;
  let claimsServiceMock: any;
  beforeEach(async () => {
    insuranceServiceMock = {
      getAllInsurancesByUsername: jest.fn()
    };
    claimsServiceMock = {
      applyForClaims: jest.fn()
    };

    await TestBed.configureTestingModule({
      declarations: [ApplyForClaimPageComponent],
      imports: [HttpClientTestingModule, NoopAnimationsModule, MatSnackBarModule],
      providers: [{
        provide: ClaimsService, useValue: claimsServiceMock,
      }, {
        provide: InsurancesService, useValue: insuranceServiceMock
      }, MatSnackBar, Overlay]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ApplyForClaimPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('constructor should initialize the form group', () => {
    expect(component.form instanceof FormGroup).toBeTruthy();
    expect(Object.keys(component.form.controls)).toEqual(['policyNo', 'name', 'insuranceType', 'claimReason', 'estimatedAmount']);
    expect(component.form.get('policyNo')?.validator).toBeDefined();
    expect(component.form.get('name')?.validator).toBeDefined();
    expect(component.form.get('claimReason')?.validator).toBeDefined();
    expect(component.form.get('estimatedAmount')?.validator).toBeDefined();
  });

  it('ngOninit should get the data from the database using service', () => {
    const response = [{ detail: 'detail1' }, { detail: 'detail2' }];
    const spy = jest.spyOn(insuranceServiceMock, 'getAllInsurancesByUsername').mockImplementation(() => of(response));
    component.username = 'TestUser';
    component.ngOnInit();
    expect(spy).toHaveBeenCalledWith('TestUser');
    expect(component.insuranceDetails).toEqual(response);
  })

  it('should submit claim request when user is available', () => {
    const mockInsurances = [{ id: '1', type: 'auto' }, { id: '2', type: 'health' }];
    component.insuranceDetails = mockInsurances;
    const claimSpy = jest.spyOn(claimsServiceMock, 'applyForClaims').mockImplementation(() => of({}));
    const claimFormValues = { policyNo: '1', name: 'TestUser', insuranceType: '', claimReason: 'Reason', estimatedAmount: 1000 };
    component.form.setValue(claimFormValues);
    component.claimRequest();
    expect(claimSpy).toHaveBeenCalledWith({
      ...claimFormValues,
      insuranceType: 'auto',
    });
  });

  it('should call the snackbar open method', () => {
    const spy = jest.spyOn(component.snackBar, 'open');
    component.openSnackBar();
    expect(spy).toHaveBeenCalledWith('Claim Submitted', 'Ok', {
      horizontalPosition: 'end',
      verticalPosition: 'top',
      duration: 1000
    });
  });

});
