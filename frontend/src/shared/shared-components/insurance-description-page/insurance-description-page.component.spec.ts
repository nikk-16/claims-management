import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InsurancePoliciesService } from '../../../app/services/insurance-policies.service';
import { InsuranceDescriptionPageComponent } from './insurance-description-page.component';
import { ActivatedRoute, Router } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { InsurancePolicies } from '../../../app/model/insurance-policies';

describe('InsuranceDescriptionPageComponent', () => {
  let component: InsuranceDescriptionPageComponent;
  let fixture: ComponentFixture<InsuranceDescriptionPageComponent>;
  let activatedRouteMock: any;
  let insurancePoliciesServiceMock: any;
  let routerMock:any
  beforeEach(async () => {
    insurancePoliciesServiceMock = {
      getInsurancePolicyById: jest.fn().mockReturnValue(of())
    };
    activatedRouteMock = {
      snapshot: {
        params: {
          id: '1'
        }
      }
    };
    routerMock={
      navigate:jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [InsuranceDescriptionPageComponent],
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [
        { provide: InsurancePoliciesService, useValue: insurancePoliciesServiceMock },
        { provide: ActivatedRoute, useValue: activatedRouteMock },
        {provide:Router,useValue:routerMock}
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(InsuranceDescriptionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize insuranceId with route snapshot param', () => {
    expect(component.insuranceId).toEqual('1');
  });

  it('should fetch insurance policy if insuranceId is not null or undefined', () => {
    const response: InsurancePolicies = {
      id:'1',
      type:'string',
      amount:100,
      maxClaim:1000,
      policyTerm:1,
      description:['a','b']
    };
    const spy=jest.spyOn(insurancePoliciesServiceMock,'getInsurancePolicyById').mockImplementation(()=>of(response));
    component.ngOnInit();
    expect(insurancePoliciesServiceMock.getInsurancePolicyById).toHaveBeenCalledWith('1');
    expect(component.insurancePolicy).toBe(response);
  });

  it('should redirect to the insurance application page when buyNewInsurance is called', () => {
    const router = TestBed.inject(Router);
    const spy = jest.spyOn(router, 'navigate');
    component.buyNewInsurance();
    expect(spy).toHaveBeenCalledWith(['insuranceApplication']);
  });
});