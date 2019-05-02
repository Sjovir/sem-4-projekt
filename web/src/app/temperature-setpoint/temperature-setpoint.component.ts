import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { DataService } from '../services/data.service';






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

  public temperatureMin = 15; //get realtime values from database
  public temperatureMax = 15; //get realtime values from database
  public temperatureAlarmMin = 50; //get realtime values from database
  public temperatureAlarmMax = 50; //get realtime values from database


  constructor(private dataService: DataService) { }

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

  public injectSetpoints(obj){
    this.dataService.getGreenhouseSetpoints(obj).subscribe(setpoints =>{
      setpoints=JSON.parse(setpoints);
      this.temperatureAlarmMax =setpoints.temperatureSetpoint.alarmMax;
      this.temperatureAlarmMin =setpoints.temperatureSetpoint.alarmMin;
      this.temperatureMax=setpoints.temperatureSetpoint.max;
      this.temperatureMin=setpoints.temperatureSetpoint.min;

    });

  }


  ngOnInit() {
  }


}
