import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyClaimsCardComponent } from './apply-claims-card.component';

describe('ApplyClaimsCardComponent', () => {
  let component: ApplyClaimsCardComponent;
  let fixture: ComponentFixture<ApplyClaimsCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplyClaimsCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyClaimsCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
