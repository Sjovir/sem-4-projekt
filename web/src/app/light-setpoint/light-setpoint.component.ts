import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-light-setpoint',
  templateUrl: './light-setpoint.component.html',
  styleUrls: ['./light-setpoint.component.css']
})
export class LightSetpointComponent implements OnInit {

  public blue = 50;
  public red = 50;
  public time = 50;

  constructor() { }

  onSlideListenerBlue(val) {
    this.blue = val;
  }
  onSlideListenerRed(val) {
    this.red = val;
  }
  onSlideListenerTime(val) {
    this.time = val;
  }

  ngOnInit() {
  }

}
