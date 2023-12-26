import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyForInsurancePageComponent } from './apply-for-insurance-page.component';

describe('ApplyForInsurancePageComponent', () => {
  let component: ApplyForInsurancePageComponent;
  let fixture: ComponentFixture<ApplyForInsurancePageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplyForInsurancePageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyForInsurancePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
