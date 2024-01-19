import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InsurancePoliciesService } from '../../services/insurance-policies.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HomePageComponent } from './home-page.component';
import { of } from 'rxjs';

describe('HomePageComponent', () => {
  let component: HomePageComponent;
  let fixture: ComponentFixture<HomePageComponent>;
  let insurancePoliciesServiceMock:any;
  beforeEach(async () => {
    insurancePoliciesServiceMock={
      getAllInsurancePolicies: jest.fn()
    };
    await TestBed.configureTestingModule({
      declarations: [HomePageComponent],
      imports: [HttpClientTestingModule],
      providers: [{
        provide:InsurancePoliciesService,useValue:insurancePoliciesServiceMock
      }]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HomePageComponent);
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
