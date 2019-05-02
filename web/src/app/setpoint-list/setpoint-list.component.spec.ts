import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetpointListComponent } from './setpoint-list.component';

describe('SetpointListComponent', () => {
  let component: SetpointListComponent;
  let fixture: ComponentFixture<SetpointListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetpointListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetpointListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
