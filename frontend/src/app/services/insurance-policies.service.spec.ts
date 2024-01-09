import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InsurancePoliciesService } from './insurance-policies.service';

describe('InsurancePoliciesService', () => {
  let service: InsurancePoliciesService;

  beforeEach(() => {
    TestBed.configureTestingModule({  imports: [HttpClientTestingModule], });
    service = TestBed.inject(InsurancePoliciesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
