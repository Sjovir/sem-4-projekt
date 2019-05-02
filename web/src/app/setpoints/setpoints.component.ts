import { Component, OnInit, ViewChild } from '@angular/core';
import { LightSetpointComponent } from '../light-setpoint/light-setpoint.component';
import { TemperatureSetpointComponent } from '../temperature-setpoint/temperature-setpoint.component';
import { HumiditySetpointComponent } from '../humidity-setpoint/humidity-setpoint.component';
import { DataService } from '../services/data.service';




@Component({
  selector: 'app-setpoints',
  templateUrl: './setpoints.component.html',
  styleUrls: ['./setpoints.component.css']
})
export class SetpointsComponent implements OnInit {

  @ViewChild(LightSetpointComponent)
  private lightSetpointsTable: LightSetpointComponent;

  @ViewChild(TemperatureSetpointComponent)
  private tempSetpoint:TemperatureSetpointComponent;

  @ViewChild(HumiditySetpointComponent)
  private humSetPoint:HumiditySetpointComponent;

  public tempSpinner:boolean =false;
  public humSpinner:boolean=false;
  public lightSpinner:boolean=false;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    
  }
  receiveGreenhouseID($event) {
    this.lightSetpointsTable.onSelect($event);
    this.tempSetpoint.injectSetpoints($event);
    this.humSetPoint.injectSetpoints($event);
   
  } 

  createSetPoints(){
    this.tempSpinner=true;
    this.tempSetpoint.writeSetpoint().subscribe(response=>{
      if(response===null){
        this.tempSpinner=false;
      }
    });

    this.humSpinner=true;
    this.humSetPoint.writeSetpoint().subscribe(response=>{
      if(response===null){
        this.humSpinner=false;
      }
    });
    
    this.lightSpinner=true;
    this.lightSetpointsTable.writeSetPoint().subscribe(response=>{
      if(response===null){
        this.lightSpinner=false;
      }
    });
  }

}
