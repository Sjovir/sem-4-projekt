import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Setpoint } from 'src/setpoint';
import { DataService } from '../services/data.service';
import { LightSetpointComponent } from '../light-setpoint/light-setpoint.component';
import { Greenhouse } from 'src/greenhouse';



@Component({
  selector: 'app-setpoint-list',
  templateUrl: './setpoint-list.component.html',
  styleUrls: ['./setpoint-list.component.css']
})
export class SetpointListComponent implements OnInit {
  setpoints: Setpoint;





  constructor() { }

  ngOnInit() {



  }


  onSelect(setpoints: Setpoint) {
    this.setpoints = setpoints;
    this.setpoints.lightSetpoints.sort(this.compare);
    //
    // console.log("hej22" + setpoints);
    // console.log("df" + setpoints.lightSetpoints);
    // console.log("temperature" + setpoints.temperatureSetpoint);
  }

  compare(a, b) {
    var startTime1 = a.startTime.split(':');
    var startTime2 = b.startTime.split(':');

    if (startTime1 > startTime2) return 1;
    if (startTime1 < startTime2) return -1;
    return 0;
  }


}
