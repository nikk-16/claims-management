import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InsurancePoliciesService } from '../../services/insurance-policies.service';
import { InsuranceDetailsPageComponent } from './insurance-details-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('InsuranceDetailsPageComponent', () => {
  let component: InsuranceDetailsPageComponent;
  let fixture: ComponentFixture<InsuranceDetailsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InsuranceDetailsPageComponent],
      imports: [HttpClientTestingModule],
      providers: [InsurancePoliciesService]
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
