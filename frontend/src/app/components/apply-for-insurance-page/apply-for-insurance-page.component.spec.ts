import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ApplyForInsurancePageComponent } from './apply-for-insurance-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InsurancesService } from '../../services/insurances.service';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';
import { formatDate } from '@angular/common';

describe('ApplyForInsurancePageComponent', () => {
  let component: ApplyForInsurancePageComponent;
  let fixture: ComponentFixture<ApplyForInsurancePageComponent>;
  let insuranceServiceMock: any;
  beforeEach(async () => {
    insuranceServiceMock = {
      applyForInsurance: jest.fn()
    };
    await TestBed.configureTestingModule({
      declarations: [ApplyForInsurancePageComponent],
      imports: [HttpClientTestingModule],
      providers: [{
        provide: InsurancesService, useValue: insuranceServiceMock
      }, FormBuilder]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ApplyForInsurancePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should format the end date and apply for insurance using the form value', () => {
    const insuranceRequestSpy = jest.spyOn(insuranceServiceMock, 'applyForInsurance').mockImplementation(() => of({}));
    const insuranceFormValues = {
      username: 'test-user',
      type: 'type1',
      amount: 1000,
      startDate: new Date(),
      endDate: '2030-11-11',
      maxClaim: 10000
    }
    component.form.setValue(insuranceFormValues);
    const formattedFormValues = {
      ...insuranceFormValues,
      endDate: formatDate(insuranceFormValues.endDate, 'yyyy-MM-dd', 'en')
    }
    component.insuranceRequest();
    expect(insuranceRequestSpy).toHaveBeenCalledWith(formattedFormValues);
  })

});
