import { TestBed } from '@angular/core/testing';
import { AuthGuard } from './auth.service';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';

describe('AuthGuard', () => {
  let service: AuthGuard;
  let router = { navigate: jest.fn() };
  const mockActivatedRouteSnapshot: ActivatedRouteSnapshot = {} as ActivatedRouteSnapshot;
  const routerStateSnapshot: RouterStateSnapshot = {} as RouterStateSnapshot;
  beforeEach(() => {
    jest.clearAllMocks();
    localStorage.clear();
    router.navigate.mockReset();
    service=new AuthGuard(router as any);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('should navigate to login if no username is stored', () => {
    const canNavigate:boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> = service.canActivate(mockActivatedRouteSnapshot, routerStateSnapshot);
    expect(canNavigate).toBe(false);
    expect(router.navigate).toHaveBeenCalledWith(['/login']);
  });
  
  it('should not navigate to login if username is stored', () => {
    localStorage.setItem('username', 'testUser');
    const canNavigate: boolean | UrlTree | Observable<boolean | UrlTree> | Promise<boolean | UrlTree> = service.canActivate(mockActivatedRouteSnapshot, routerStateSnapshot);
    expect(canNavigate).toBe(true);
    expect(router.navigate).not.toHaveBeenCalled();
  });


});
