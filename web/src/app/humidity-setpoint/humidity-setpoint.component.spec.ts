import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HumiditySetpointComponent } from './humidity-setpoint.component';

describe('HumiditySetpointComponent', () => {
  let component: HumiditySetpointComponent;
  let fixture: ComponentFixture<HumiditySetpointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HumiditySetpointComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HumiditySetpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
