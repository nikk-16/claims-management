import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InsurancesService } from './insurances.service';

describe('InsurancesService', () => {
  let service: InsurancesService;

  beforeEach(() => {
    TestBed.configureTestingModule({  imports: [HttpClientTestingModule],});
    service = TestBed.inject(InsurancesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
