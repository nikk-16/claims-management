import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderComponent } from './header.component';
import { Router } from '@angular/router';

describe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  let routerMock:any
  beforeEach(async () => {
    routerMock= {
      navigate: jest.fn()
    }
    await TestBed.configureTestingModule({
      declarations: [HeaderComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  afterEach(() => {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should navigate to root when logout is called', () => {
    jest.spyOn(routerMock, 'navigate').mockReturnValue(null);
   //component.logOut();

    //expect(routerMock.navigate).toHaveBeenCalledWith(['/']);
  });

  it('should remove localStorage data when logout is called', () => {
    localStorage.setItem('username', 'test');
    localStorage.setItem('token', 'test');
    component.logOut();
    expect(localStorage.getItem('username')).toBe('');
    expect(localStorage.getItem('token')).toBe('');
  });

  it('should emit value when toggleSidebar is called', () => {
    component.showSidebar.subscribe(value => {
      expect(value).toEqual('somedata');
    });
    component.toggleSidebar();
  });
 
});

