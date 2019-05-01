import { Component, OnInit } from '@angular/core';

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

  constructor() { }

  onSlideListenerBlue(val) {
    this.blue = val;
  }
  onSlideListenerRed(val) {
    this.red = val;
  }

  ngOnInit() {
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
