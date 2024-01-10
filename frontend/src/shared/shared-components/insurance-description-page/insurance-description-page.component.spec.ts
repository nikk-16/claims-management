import { ComponentFixture, TestBed } from '@angular/core/testing';
import { InsurancePoliciesService } from '../../../app/services/insurance-policies.service';
import { InsuranceDescriptionPageComponent } from './insurance-description-page.component';
import { ActivatedRoute } from '@angular/router';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

describe('InsuranceDescriptionPageComponent', () => {
  let component: InsuranceDescriptionPageComponent;
  let fixture: ComponentFixture<InsuranceDescriptionPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InsuranceDescriptionPageComponent],
      imports: [HttpClientTestingModule,RouterTestingModule],
      providers:[InsurancePoliciesService]
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