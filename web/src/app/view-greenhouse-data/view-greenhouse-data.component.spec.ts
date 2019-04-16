import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewGreenhouseDataComponent } from './view-greenhouse-data.component';

describe('ViewGreenhouseDataComponent', () => {
  let component: ViewGreenhouseDataComponent;
  let fixture: ComponentFixture<ViewGreenhouseDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ViewGreenhouseDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewGreenhouseDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
