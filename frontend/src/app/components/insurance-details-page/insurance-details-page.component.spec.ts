import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { InsurancePoliciesService } from '../../services/insurance-policies.service';
import { InsuranceDetailsPageComponent } from './insurance-details-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

describe('InsuranceDetailsPageComponent', () => {
  let component: InsuranceDetailsPageComponent;
  let fixture: ComponentFixture<InsuranceDetailsPageComponent>;
  let insurancePoliciesServiceMock:any;
  beforeEach(async () => {
    insurancePoliciesServiceMock={
      getAllInsurancePolicies: jest.fn()
    };
    await TestBed.configureTestingModule({
      declarations: [InsuranceDetailsPageComponent],
      imports: [HttpClientTestingModule],
      providers: [{
        provide:InsurancePoliciesService,useValue:insurancePoliciesServiceMock
      }]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsuranceDetailsPageComponent);
    component = fixture.componentInstance;
   
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should getAllInsurancePolicies on initialization and assign it to insurancePolicies ',()=>{
    const response=[{id:'hvbuy3465'},{id:'hjgdajkahxk'}]
    const spy=jest.spyOn(insurancePoliciesServiceMock,'getAllInsurancePolicies').mockImplementation(()=>of(response));
    fixture.detectChanges();
    expect(component.insurancePolicies).toEqual(response);
  })
});
