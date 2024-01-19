import { ComponentFixture, TestBed } from '@angular/core/testing';
import { UsersService } from '../../services/users.service';
import { InsurancesService } from '../../services/insurances.service';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ProfileComponent } from './profile.component';
import { of } from 'rxjs';

describe('ProfileComponent', () => {
  let component: ProfileComponent;
  let fixture: ComponentFixture<ProfileComponent>;
  let insuranceServiceMock:any;
  let userServiceMock:any;
  beforeEach(async () => {
    insuranceServiceMock = {
      getAllInsurancesByUsername: jest.fn()
    };
    userServiceMock={
      getUserByUsername:jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [ProfileComponent],
      imports: [HttpClientTestingModule],
      providers: [
        {
          provide:UsersService,useValue:userServiceMock
        },{
          provide:InsurancesService,useValue:insuranceServiceMock
        }
      ]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should getAllInsurances of logged in user using username',() => {
    const responseInsurances = [{ detail: 'detail1' }, { detail: 'detail2' }];
    const responseUserDetails = { detail: 'userDetail' };
  
    const spyInsurances = jest.spyOn(insuranceServiceMock, 'getAllInsurancesByUsername').mockImplementation(() => of(responseInsurances));
    const spyUserDetails = jest.spyOn(userServiceMock, 'getUserByUsername').mockImplementation(() => of(responseUserDetails));
  
    component.username = 'TestUser';
    component.ngOnInit();
  
    expect(spyInsurances).toHaveBeenCalledWith('TestUser');
    expect(spyUserDetails).toHaveBeenCalledWith('TestUser');
    
    expect(component.insurances).toEqual(responseInsurances);
    expect(component.userDetails).toEqual(responseUserDetails);
  }); 
});
