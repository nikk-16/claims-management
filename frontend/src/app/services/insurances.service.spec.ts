import { AppConstant } from '../util/constants';
import { InsurancesService } from './insurances.service';
import { of } from 'rxjs';

describe('InsurancesService', () => {
  let service: InsurancesService;
  let httpClient = { get: jest.fn(), post: jest.fn() };
  beforeEach(() => {
    jest.clearAllMocks();
    service = new InsurancesService(httpClient as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('getAllInsurancesByUsername should return value of type observable', async () => {
    const username = 'meghana'
    const result = [{
      "_id": {
        "$oid": "659bce7ec5b7d7e7afe183ac"
      },
      "username": "meghana",
      "type": "health",
      "amount": 200000,
      "startDate": "2024-01-04",
      "endDate": "2024-01-31",
      "maxClaim": 500000,
      "_class": "com.claims.manage.models.Insurance"
    }];
    httpClient.get.mockReturnValue(of(result));
    service.getAllInsurancesByUsername(username).subscribe(response => {
      expect(response).toEqual(result);
    })
    expect(httpClient.get).toHaveBeenCalledWith(`http://localhost:8080/insurance/${username}`)
  });

  it('applyForInsurance should post the data to insurance service', () => {
    const newInsurance = { plan: 'Premium', amount: 1000 };
    const response = { plan: 'Premium', amount: 1000 };
    httpClient.post.mockReturnValue(of(response));
    service.applyForInsurance(newInsurance).subscribe(data => {
      expect(data).toEqual(response);
    });
    expect(httpClient.post).toHaveBeenCalledWith(`${AppConstant.INSURANCE_URI}/buy`, newInsurance, { responseType: 'json' });
  });

});
