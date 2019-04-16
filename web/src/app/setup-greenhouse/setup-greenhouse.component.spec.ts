import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetupGreenhouseComponent } from './setup-greenhouse.component';

describe('SetupGreenhouseComponent', () => {
  let component: SetupGreenhouseComponent;
  let fixture: ComponentFixture<SetupGreenhouseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetupGreenhouseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetupGreenhouseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
