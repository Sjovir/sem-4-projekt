import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TemperatureSetpointComponent } from './temperature-setpoint.component';

describe('TemperatureSetpointComponent', () => {
  let component: TemperatureSetpointComponent;
  let fixture: ComponentFixture<TemperatureSetpointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TemperatureSetpointComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TemperatureSetpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
