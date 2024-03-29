import { Component, OnInit } from '@angular/core';
import { DataService } from '../services/data.service';
import { Observable } from 'rxjs';

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
  private greenhouseid=-1;

  constructor(private dataService:DataService) { }

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

  public injectSetpoints(obj){
    this.greenhouseid=obj;
    this.dataService.getGreenhouseSetpoints(obj).subscribe(setpoints =>{
      setpoints=JSON.parse(setpoints);
      if(setpoints.humiditySetpoint!==null){
        this.humidityAlarmMax =setpoints.humiditySetpoint.alarmMax;
        this.humidityAlarmMin =setpoints.humiditySetpoint.alarmMin;
        this.humidityMax=setpoints.humiditySetpoint.max;
        this.humidityMin=setpoints.humiditySetpoint.min;
      }

    });

  }
  public writeSetpoint():Observable<any>{
    if(!(this.greenhouseid===-1)){
      return this.dataService.writeHumiditySetpoint(this.greenhouseid, Number(this.humidityMin), Number(this.humidityMax), Number(this.humidityAlarmMin), Number(this.humidityAlarmMax));
    }
  }

}
