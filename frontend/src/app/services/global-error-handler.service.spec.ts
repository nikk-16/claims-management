import { TestBed } from '@angular/core/testing';

import { GlobalErrorHandlerService } from './global-error-handler.service';

describe('GlobalErrorHandlerService', () => {
  let service: GlobalErrorHandlerService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GlobalErrorHandlerService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
  it('should log the error message', () => {
    const error = new Error('Test error');
    const logSpy = jest.spyOn(console, 'error');
    service.handleError(error);
    expect(logSpy).toHaveBeenCalledWith('error occured:------', 'Test error');
    logSpy.mockRestore();
  });

  it('should handle error even when error object does not have message property', () => {
    const error = {};
    const logSpy = jest.spyOn(console, 'error');
    service.handleError(error);
    expect(logSpy).toHaveBeenCalledWith('error occured:------', undefined);
    logSpy.mockRestore();
  });
});
