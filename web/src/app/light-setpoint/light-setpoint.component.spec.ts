import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LightSetpointComponent } from './light-setpoint.component';

describe('LightSetpointComponent', () => {
  let component: LightSetpointComponent;
  let fixture: ComponentFixture<LightSetpointComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LightSetpointComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LightSetpointComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
