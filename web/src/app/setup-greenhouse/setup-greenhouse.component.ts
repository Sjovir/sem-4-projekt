import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { DataService } from 'src/app/services/data.service';

@Component({
  selector: 'app-setup-greenhouse',
  templateUrl: './setup-greenhouse.component.html',
  styleUrls: ['./setup-greenhouse.component.css']
})
export class SetupGreenhouseComponent implements OnInit {

  setupForm: FormGroup;
  responseMessage: string;

  constructor(private formBuilder: FormBuilder, private dataService: DataService) {
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
      this.responseMessage = "Please enter a valid IP address.";
      return false;
    }

    if(!(validFormPort && formPort.length == 4)) {
      console.log(formPort);
      this.responseMessage = "Your port must be 4 digits long.";
      return false;
    }

    if(!(validFormLocation)) {
      console.log(formLocation);
      this.responseMessage = "Please enter a valid geolocation.";
      return false;
    }

    if(!(validFormName && (formName.length >= 3 && formName.length <= 30))) {
      console.log(formName);
      this.responseMessage = "Your greenhouse name must be between 3 and 30 characters and/or numbers.";
      return false;
    }

    this.doRestCall(formIPAddress, formPort, formLocation, formName);
    this.responseMessage = "You created an greenhouse.";
    return true;
  }

  async doRestCall(ipAddress: string, port: number, location: string, name: string) {
    const res = await this.dataService.setupGreenhouse(ipAddress, port, location, name).toPromise();
    console.log("REST CALL");
    this.writeGmsConnection();
    return true;
  }

  async writeGmsConnection() {
    const res = await this.dataService.getGreenhouses().toPromise();
    var greenhouses=JSON.parse(res);
    var id=greenhouses[greenhouses.length-1].id;
    var resCall = await this.dataService.writeGmsConnection(id,"10.123.3.53",7001).toPromise();
    return true;
  }
}
