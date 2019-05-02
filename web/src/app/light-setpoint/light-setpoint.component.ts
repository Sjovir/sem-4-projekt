import { Component, OnInit, Output, EventEmitter, ViewChild } from '@angular/core';
import { DataService } from '../services/data.service';
import { Greenhouse } from '../../greenhouse';
import { Setpoint } from 'src/setpoint';
import { SetpointListComponent } from '../setpoint-list/setpoint-list.component';

@Component({
  selector: 'app-light-setpoint',
  templateUrl: './light-setpoint.component.html',
  styleUrls: ['./light-setpoint.component.css']
})
export class LightSetpointComponent implements OnInit {

  public blue = 50;
  public red = 50;
  public time: string;
  public responseMessage: string;

  public selectedGreenhouse: Greenhouse;

  @ViewChild(SetpointListComponent)
  private lightSetpointsTable: SetpointListComponent;

  constructor(private dataService: DataService) { }




  onSlideListenerBlue(val) {
    this.blue = val;
  }
  onSlideListenerRed(val) {
    this.red = val;
  }

  ngOnInit() {
  }

  async onSelect(greenhouseid: number) {
    //this.dataService.getGreenhouseSetpoints(greenhouseid).subscribe(setpoints => {
    //  this.setpoints = JSON.parse(setpoints)
      
    //});

    let setpoints = await this.dataService.getGreenhouseSetpoints(greenhouseid).toPromise();

    this.lightSetpointsTable.onSelect(JSON.parse(setpoints));
    console.log(greenhouseid);
  }

  
  checkFields() {

   
    var selectedTime = (<HTMLInputElement>document.getElementById("time")).value;

    var validTime = /^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$/.test(selectedTime);

    if (validTime != true) {
      console.log(selectedTime);
      this.responseMessage = "Please enter a valid time for this setpoint.";
      return false;
    }

    this.doRestCall();
    console.log("Time set to: " + selectedTime);
    this.responseMessage = "Time set to: " + selectedTime;
    return true;
  }

  doRestCall() {
    console.log("REST CALL");
    return true;
  }

}
