import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-humidity-setpoint',
  templateUrl: './humidity-setpoint.component.html',
  styleUrls: ['./humidity-setpoint.component.css']
})
export class HumiditySetpointComponent implements OnInit {

  public humidityMin = 50;
  public humidityMax = 50;
  public humidityAlarmMin = 50;
  public humidityAlarmMax = 50;

  constructor() { }

  onSlideListenerMin(val) {
    this.humidityMin = val;
  }
  onSlideListenerMax(val) {
    this.humidityMax = val;
  }
  onSlideListenerAlarmMin(val) {
    this.humidityAlarmMin = val;
  }
  onSlideListenerAlarmMax(val) {
    this.humidityAlarmMax = val;
  }


  ngOnInit() {
  }

}
