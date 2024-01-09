import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ClaimsDetailsPageComponent } from './claims-details-page.component';

describe('ClaimsDetailsPageComponent', () => {
  let component: ClaimsDetailsPageComponent;
  let fixture: ComponentFixture<ClaimsDetailsPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClaimsDetailsPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ClaimsDetailsPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
