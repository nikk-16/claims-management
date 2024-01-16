import { of } from 'rxjs';
import { ClaimsService } from './claims.service';
import { AppConstant } from '../util/constants';

describe('ClaimsService', () => {
  let service: ClaimsService;
  let httpClient={get:jest.fn(),post:jest.fn()};
  beforeEach(() => {
    jest.clearAllMocks();
    service=new ClaimsService(httpClient as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('applyForClaims should post the data to the claims',()=>{
    const newClaim={"id":"345","amount":12345};
    const result={"id":"345","amount":12345};
    httpClient.post.mockReturnValue(of(result));
    service.applyForClaims(newClaim).subscribe(response=>{
      expect(response).toEqual(result);
    });
    expect(httpClient.post).toHaveBeenCalledWith(`${AppConstant.CLAIMS_URI}/apply`,newClaim,{ responseType:'json'});
  });
   
});
