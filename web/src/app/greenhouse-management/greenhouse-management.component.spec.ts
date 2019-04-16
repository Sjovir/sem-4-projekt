import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GreenhouseManagementComponent } from './greenhouse-management.component';

describe('GreenhouseManagementComponent', () => {
  let component: GreenhouseManagementComponent;
  let fixture: ComponentFixture<GreenhouseManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GreenhouseManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GreenhouseManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
