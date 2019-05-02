import { Component, OnInit, ViewChild } from '@angular/core';
import { LightSetpointComponent } from '../light-setpoint/light-setpoint.component';
import { TemperatureSetpointComponent } from '../temperature-setpoint/temperature-setpoint.component';
import { HumiditySetpointComponent } from '../humidity-setpoint/humidity-setpoint.component';




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
  private humSetPoint:TemperatureSetpointComponent;
  constructor() { }

  ngOnInit() {
    
  }
  receiveGreenhouseID($event) {
    this.lightSetpointsTable.onSelect($event);
    this.tempSetpoint.injectSetpoints($event);
    this.humSetPoint.injectSetpoints($event);
   
  } 

}
