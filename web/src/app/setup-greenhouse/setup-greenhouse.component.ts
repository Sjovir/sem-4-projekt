import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-setup-greenhouse',
  templateUrl: './setup-greenhouse.component.html',
  styleUrls: ['./setup-greenhouse.component.css']
})
export class SetupGreenhouseComponent implements OnInit {

  setupForm: FormGroup;

  constructor(private formBuilder: FormBuilder) {
    this.setupForm = this.formBuilder.group({
      ipAddress: ['', Validators.required],
      port: ['', Validators.required],
      location: ['', Validators.required],
      name: ['', Validators.required]
    })
  }

  ngOnInit() {
  }

  checkFields() {
    var formIPAddress = this.setupForm.controls.ipAddress.value;
    var validFormIPAddress = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(formIPAddress);

    var formPort = this.setupForm.controls.port.value;
    var validFormPort = /^[0-9]+$/.test(formPort);

    var formLocation = this.setupForm.controls.location.value;
    var validFormLocation = /^[-+]?([1-8]?\d(\.\d+)?|90(\.0+)?),\s*[-+]?(180(\.0+)?|((1[0-7]\d)|([1-9]?\d))(\.\d+)?)$/.test(formLocation);

    var formName = this.setupForm.controls.name.value;
    var validFormName = /^[0-9a-zA-Z]+$/.test(formName);

    if(!(validFormIPAddress)) {
      console.log(formIPAddress);
      this.htmlErrorDiv = "Please enter a valid IP address.";
      return false;
    }

    if(!(validFormPort && formPort.length == 4)) {
      console.log(formPort);
      this.htmlErrorDiv = "Your port must be 4 digits long.";
      return false;
    }

    if(!(validFormLocation)) {
      console.log(formLocation);
      this.htmlErrorDiv = "Please enter a valid geolocation.";
      return false;
    }

    if(!(validFormName && (formName.length >= 3 && formName.length <= 30))) {
      console.log(formName);
      this.htmlErrorDiv = "Your greenhouse name must be between 3 and 30 characters and/or numbers.";
      return false;
    }

    this.doRestCall();
    this.htmlErrorDiv = "You created an greenhouse.";
    return true;
  }

  doRestCall() {
    console.log("REST CALL");
    return true;
  }
}
