import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClaimsService } from '../../services/claims.service';
import { InsurancesService } from '../../services/insurances.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ApplyForClaimPageComponent } from './apply-for-claim-page.component';

describe('ApplyForClaimPageComponent', () => {
  let component: ApplyForClaimPageComponent;
  let fixture: ComponentFixture<ApplyForClaimPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ApplyForClaimPageComponent],
      imports: [HttpClientTestingModule],
      providers: [ClaimsService,InsurancesService]
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
