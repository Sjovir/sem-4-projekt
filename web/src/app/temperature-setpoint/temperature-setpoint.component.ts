import { Component, OnInit } from '@angular/core';
import { NgModule } from '@angular/core';
import { FormsModule, NgModel } from '@angular/forms';
import { DataService } from '../services/data.service';
import { Observable } from 'rxjs';






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
  private greenhouseid=-1;


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
    this.greenhouseid=obj;
    this.dataService.getGreenhouseSetpoints(obj).subscribe(setpoints =>{
      setpoints=JSON.parse(setpoints);
      if(setpoints.temperatureSetpoint!==null){
        this.temperatureAlarmMax =setpoints.temperatureSetpoint.alarmMax;
        this.temperatureAlarmMin =setpoints.temperatureSetpoint.alarmMin;
        this.temperatureMax=setpoints.temperatureSetpoint.max;
        this.temperatureMin=setpoints.temperatureSetpoint.min;
      }

    });

  }

  public writeSetpoint():Observable<any>{
    if(!(this.greenhouseid===-1)){
      return this.dataService.writeTemperatureSetpoint(this.greenhouseid, Number(this.temperatureMin), Number(this.temperatureMax), Number(this.temperatureAlarmMin), Number(this.temperatureAlarmMax));
    }
  }


  ngOnInit() {
  }


}
