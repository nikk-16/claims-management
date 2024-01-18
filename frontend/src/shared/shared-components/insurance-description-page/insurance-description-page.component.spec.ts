import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InsuranceDescriptionPageComponent } from './insurance-description-page.component';

describe('InsuranceDescriptionPageComponent', () => {
  let component: InsuranceDescriptionPageComponent;
  let fixture: ComponentFixture<InsuranceDescriptionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InsuranceDescriptionPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(InsuranceDescriptionPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});