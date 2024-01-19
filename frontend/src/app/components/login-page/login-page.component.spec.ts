import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { UsersService } from '../../services/users.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { LoginPageComponent } from './login-page.component';
import { of } from 'rxjs';
import { Router } from '@angular/router';


describe('LoginPageComponent', () => {
  let component: LoginPageComponent;
  let fixture: ComponentFixture<LoginPageComponent>;
  let usersServiceMock:any
  let routerMock:any
  beforeEach(async () => {
    usersServiceMock={
      login:jest.fn()
    }
    routerMock={
      navigate:jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [LoginPageComponent],
      imports: [HttpClientTestingModule],
      providers: [{
        provide:UsersService,useValue:usersServiceMock
      },{
        provide:Router,useValue:routerMock
      }]
    })
      .compileComponents();

    fixture = TestBed.createComponent(LoginPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should log the user in, store their name and token and navigate home', () => {
    const response = { token: 'testToken' };
    usersServiceMock.login.mockReturnValue(of(response));
    component.form.controls['username'].setValue('testUser');
    component.form.controls['password'].setValue('testPassword');
    component.login();
    expect(usersServiceMock.login).toHaveBeenCalledWith('testUser', 'testPassword');
    expect(localStorage.getItem('username')).toEqual('testUser');
    expect(localStorage.getItem('token')).toEqual('testToken');
    expect(routerMock.navigate).toHaveBeenCalledWith(['/home']);
  });
});
