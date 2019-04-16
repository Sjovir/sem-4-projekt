import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GreenhouseCommandComponent } from './greenhouse-command.component';

describe('GreenhouseCommandComponent', () => {
  let component: GreenhouseCommandComponent;
  let fixture: ComponentFixture<GreenhouseCommandComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GreenhouseCommandComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GreenhouseCommandComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
