import { Component, OnInit, ViewChild } from '@angular/core';
import { LightSetpointComponent } from '../light-setpoint/light-setpoint.component';




@Component({
  selector: 'app-setpoints',
  templateUrl: './setpoints.component.html',
  styleUrls: ['./setpoints.component.css']
})
export class SetpointsComponent implements OnInit {

  @ViewChild(LightSetpointComponent)
  private lightSetpointsTable: LightSetpointComponent;
  constructor() { }

  ngOnInit() {
    
  }
  receiveGreenhouseID($event) {
    this.lightSetpointsTable.onSelect($event);
   
  } 

}
