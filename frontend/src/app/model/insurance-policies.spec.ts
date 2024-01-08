import { InsurancePolicies } from './insurance-policies';

describe('InsurancePolicies', () => {
  it('should create an instance', () => {
    expect(new InsurancePolicies('skfhoehfoeh8273','health',1220,122230,1,['shff df','aqeoi dsoifu'])).toBeTruthy();
  });
});
