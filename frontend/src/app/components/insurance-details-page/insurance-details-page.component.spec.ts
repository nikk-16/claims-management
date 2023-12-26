import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceDetailsPageComponent } from './insurance-details-page.component';

describe('InsuranceDetailsPageComponent', () => {
  let component: InsuranceDetailsPageComponent;
  let fixture: ComponentFixture<InsuranceDetailsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InsuranceDetailsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsuranceDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
