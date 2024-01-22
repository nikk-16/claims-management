import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AppComponent } from './app.component';
import { Router } from '@angular/router';
import { of } from 'rxjs';

describe('AppComponent', () => {
  let router: Router
  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule],
    declarations: [AppComponent],
    providers: [
      { provide: Router, useValue: { events: of({}) } }
    ]
  }));

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'claims-management'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('claims-management');
  });

  it('should set username on ngOnInit', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const mockUsername = 'testUser';
    localStorage.setItem('username', mockUsername);
    app.ngOnInit();
    //expect(app.username).toBe(mockUsername);
  });

  it('should toggle side when put method is called', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    app.side = true;
    app.put('');
    expect(app.side).toBe(false);
  });

  it('should subscribe to router events on init', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const router = TestBed.inject(Router);
    const spy = jest.spyOn(router.events, 'subscribe');
    localStorage.setItem('username', 'testUsername');
    app.ngOnInit();
    expect(spy).toHaveBeenCalled();
  });

  it('should set username from local storage on init', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    const expectedUsername = 'testUsername';
    localStorage.setItem('username', expectedUsername);
    app.ngOnInit();
    expect(app.username).toEqual(expectedUsername);
  });

});

