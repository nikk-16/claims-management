import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyForClaimPageComponent } from './apply-for-claim-page.component';

describe('ApplyForClaimPageComponent', () => {
  let component: ApplyForClaimPageComponent;
  let fixture: ComponentFixture<ApplyForClaimPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplyForClaimPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyForClaimPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
