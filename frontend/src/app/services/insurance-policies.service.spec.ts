import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { InsurancePoliciesService } from './insurance-policies.service';
import { of } from 'rxjs';
import { AppConstant } from '../util/constants';

describe('InsurancePoliciesService', () => {
  let service: InsurancePoliciesService;
  let httpClient = { get: jest.fn(), post: jest.fn() };
  beforeEach(() => {
    jest.clearAllMocks();
    service=new InsurancePoliciesService(httpClient as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getAllInsurancePolicies should get all insurance policies',()=>{
    const result=[{'id':"1"}]
    httpClient.get.mockReturnValue(of(result));
    service.getAllInsurancePolicies().subscribe(response=>{
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`${AppConstant.INSURANCE_POLICIES_URI}/getall`);
  });

  it('getInsurancePolicyById should get insurance policy according to given id',()=>{
    const result=[{'id':"123"}]
    const id="123";
    httpClient.get.mockReturnValue(of(result));
    service.getInsurancePolicyById(id).subscribe(response=>{
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`${AppConstant.INSURANCE_POLICIES_URI}/${id}`);
  });
});
