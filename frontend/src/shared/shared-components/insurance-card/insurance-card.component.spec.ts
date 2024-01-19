import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceCardComponent } from './insurance-card.component';
import { Router } from '@angular/router';

describe('InsuranceCardComponent', () => {
  let component: InsuranceCardComponent;
  let fixture: ComponentFixture<InsuranceCardComponent>;
  let routermock:any;
  beforeEach(async () => {
    routermock={
      navigate:jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [InsuranceCardComponent],
      providers:[{
        provide:Router,useValue:routermock
      }]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsuranceCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to correct route on toViewInsurance call', () => {
    component.insurancePolicy = { id: 1 };
    component.toViewInsurance(Event);
    expect(routermock.navigate).toHaveBeenCalledWith(['insurances', component.insurancePolicy.id]);
  });
});
