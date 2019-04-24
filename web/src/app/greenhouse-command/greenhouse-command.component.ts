import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-greenhouse-command',
  templateUrl: './greenhouse-command.component.html',
  styleUrls: ['./greenhouse-command.component.css']
})
export class GreenhouseCommandComponent implements OnInit {

  commandForm: FormGroup;
  responseMessage: string;
  selectedTypeNumber: number;

  constructor(private formBuilder: FormBuilder) {
    this.commandForm = this.formBuilder.group({
      greenHouseID: ['', Validators.required],
      commandValue: ['', Validators.required]
    })
  }

  ngOnInit() {
  }

  checkFields() {
    var formGreenhouseID = this.commandForm.controls.greenHouseID.value;
    var selectedType = (<HTMLInputElement>document.getElementById("commandType")).value;
    var formValue = this.commandForm.controls.commandValue.value;

    if((formGreenhouseID == "" || (formGreenhouseID < 0))) {
      console.log(formGreenhouseID);
      this.responseMessage = "Please enter a valid greenhouseID";
      return false;
    }

    switch(selectedType) {
      case "Temperature":
        if (formValue == "" || !(formValue >= 0 && formValue <= 30)) {
          console.log(formValue);
          this.responseMessage = "Please enter an value between 0 and 30";
          return false;
        } else {
          this.selectedTypeNumber = 5;
        }
        break;
      case "Fan speed":
        if (formValue == "" || !(formValue == 0 || formValue == 1 || formValue == 2)) {
          console.log(formValue);
          this.responseMessage = "Please enter 0, 1 or 2";
          return false;
        } else {
          this.selectedTypeNumber = 7;
        }
        break;
      case "Blue light":
        if (formValue == "" || !(formValue >= 0 && formValue <= 100)) {
          console.log(formValue);
          this.responseMessage = "Please enter an value between 0 and 100";
          return false;
        } else {
          this.selectedTypeNumber = 8;
        }
        break;
      case "Red light":
        if (formValue == "" || !(formValue >= 0 && formValue <= 100)) {
          console.log(formValue);
          this.responseMessage = "Please enter an value between 0 and 100";
          return false;
        } else {
          this.selectedTypeNumber = 9;
        }
        break;
      case "Moisture":
        if (formValue == "" || !(formValue >= 10 && formValue <= 90)) {
          console.log(formValue);
          this.responseMessage = "Please enter an value between 10 and 90";
          return false;
        } else {
          this.selectedTypeNumber = 10;
        }
        break;
      default:
        this.responseMessage = "Please pick a type";
    }

    this.doRestCall();
    console.log("GreenhouseID: " + formGreenhouseID + ", Selected type: " + selectedType + ", Selected type number: "
                + this.selectedTypeNumber + ", Form value: " + formValue);
    this.responseMessage = "You send an command to the greenhouse.";
    return true;
  }

  doRestCall() {
    console.log("REST CALL");
    return true;
  }
}
