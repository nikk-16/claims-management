import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SignUpPageComponent } from './sign-up-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('SignUpPageComponent', () => {
  let component: SignUpPageComponent;
  let fixture: ComponentFixture<SignUpPageComponent>;
  let userServiceMock: any;
  let routerMock: any;
  beforeEach(async () => {
    userServiceMock = {
      signUp: jest.fn()
    };
    routerMock = {
      navigate: jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [SignUpPageComponent],
      imports: [HttpClientTestingModule],
      providers: [{
        provide: UsersService, useValue: userServiceMock
      }, {
        provide: Router, useValue: routerMock
      }]
    })
      .compileComponents();

    fixture = TestBed.createComponent(SignUpPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should return username form control through getter', () => {
    expect(component.username).toEqual(component.form.controls['username']);
  });

  it('should return mobile form control through getter', () => {
    expect(component.mobile).toEqual(component.form.controls['mobile']);
  });

  it('should return email form control through getter', () => {
    expect(component.email).toEqual(component.form.controls['email']);
  });

  it('should return password form control through getter', () => {
    expect(component.password).toEqual(component.form.controls['password']);
  });

  it('should return confirmPassword form control through getter', () => {
    expect(component.confirmPassword).toEqual(component.form.controls['confirmPassword']);
  });

  it('should validate password match', () => {
    const control = component.fb.group({
      password: ['password'],
      confirmPassword: ['passwordMismatch']
    });
    let result = component.passwordMatchValidator(control);
    expect(result).toEqual({ passwordMisMatch: true });
    control.setValue({
      password: 'samePassword',
      confirmPassword: 'samePassword'
    });
    result = component.passwordMatchValidator(control);
    expect(result).toEqual(null);
  });

  it('should call userService.signUp with form value and navigate to home on success', () => {
    userServiceMock.signUp.mockReturnValue(of({}));
    component.form.patchValue({
      username: 'Test User',
      email: 'test@test.com', mobile: '1234567890',
      password: 'password', confirmPassword: 'password'
    });
    component.signUp();
    expect(userServiceMock.signUp).toHaveBeenCalledWith({
      username: 'Test User',
      email: 'test@test.com',
      mobile: '1234567890',
      password: 'password'
    });
    expect(routerMock.navigate).toHaveBeenCalledWith(['/home']);
    expect(localStorage.getItem('username')).toBe('Test User');
  });

});
