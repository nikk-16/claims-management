import { TestBed } from '@angular/core/testing';
import { HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ClaimsInterceptor } from './claims.interceptor';
import { of, throwError } from 'rxjs';

describe('ClaimsInterceptor', () => {
  let claimsInterceptor: ClaimsInterceptor;
  let httpTestingController: HttpTestingController;
  let httpHandler: HttpHandler;
  let req: HttpRequest<any>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [
        ClaimsInterceptor
      ]
    });

    claimsInterceptor = TestBed.inject(ClaimsInterceptor);
    httpTestingController = TestBed.inject(HttpTestingController);
    httpHandler = { handle: jest.fn() } as HttpHandler;
    req = new HttpRequest('GET', '/');
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(claimsInterceptor).toBeTruthy();
  });

  it('should add Authorization header if token is available', () => {
    localStorage.setItem('token', 'test');
    const mockHandle = httpHandler.handle as jest.Mock;
    mockHandle.mockReturnValue(of(true));

    claimsInterceptor.intercept(req, httpHandler).subscribe(() => { });

    expect(mockHandle).toHaveBeenCalledWith(req.clone({
      headers: req.headers.set('Authorization', 'Bearer ' + 'test'),
    }));
  });

  it('should not add Authorization header if token is not available', () => {
    localStorage.removeItem('token');
    const mockHandle = httpHandler.handle as jest.Mock;
    mockHandle.mockReturnValue(of(true));

    claimsInterceptor.intercept(req, httpHandler).subscribe(() => { });

    expect(mockHandle).toHaveBeenCalledWith(req);
  });

  it('should throw server error', () => {
    const mockHandle = httpHandler.handle as jest.Mock;
    const mockErr = new HttpErrorResponse({
      status: 500,
      statusText: 'Server Error'
    });
    mockHandle.mockReturnValue(throwError(() => mockErr));

    claimsInterceptor.intercept(req, httpHandler).subscribe({
      next: res => fail('Expected error to be thrown'),
      error: err => expect(err.message).toContain('Server error: 500')
    });

    expect(mockHandle).toHaveBeenCalledWith(req);
  });
});
