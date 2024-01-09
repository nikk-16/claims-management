import { ComponentFixture, TestBed } from '@angular/core/testing';
import { SignUpPageComponent } from './sign-up-page.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { UsersService } from '../../services/users.service';

describe('SignUpPageComponent', () => {
  let component: SignUpPageComponent;
  let fixture: ComponentFixture<SignUpPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SignUpPageComponent],
      imports: [HttpClientTestingModule],
      providers: [UsersService]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SignUpPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
