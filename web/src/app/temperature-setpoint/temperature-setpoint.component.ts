import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';






@Component({
  selector: 'app-temperature-setpoint',
  templateUrl: './temperature-setpoint.component.html',
  styleUrls: ['./temperature-setpoint.component.css']
})

  @NgModule({
    imports: [FormsModule, NgModel],
    declarations: [],
    bootstrap: []
})




export class TemperatureSetpointComponent implements OnInit {

  public temperatureMin = 50; //get realtime values from database
  public temperatureMax = 50; //get realtime values from database
  public temperatureAlarmMin = 50; //get realtime values from database
  public temperatureAlarmMax = 50; //get realtime values from database
  

  constructor() { }

  onSlideListenerMin(val) {
      this.temperatureMin = val;
  }
  onSlideListenerMax(val) {
    this.temperatureMax = val;
  }
  onSlideListenerAlarmMin(val) {
    this.temperatureAlarmMin = val;
  }
  onSlideListenerAlarmMax(val) {
    this.temperatureAlarmMax = val;
  }



  ngOnInit() {
  }


}
