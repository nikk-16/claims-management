import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyClaimsComponent } from './apply-claims.component';

describe('ApplyClaimsComponent', () => {
  let component: ApplyClaimsComponent;
  let fixture: ComponentFixture<ApplyClaimsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplyClaimsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ApplyClaimsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
