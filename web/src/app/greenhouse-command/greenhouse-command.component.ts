import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';
import { Greenhouse } from 'src/greenhouse';
import { GreenhouseService } from '../greenhouse.service';

@Component({
  selector: 'app-greenhouse-command',
  templateUrl: './greenhouse-command.component.html',
  styleUrls: ['./greenhouse-command.component.css']
})
export class GreenhouseCommandComponent implements OnInit {

  commandForm: FormGroup;
  regulatorForm: FormGroup;
  responseMessage: string;
  responseMessageReg: string;
  selectedTypeNumber: number;

  public selectedGreenhouse:Greenhouse;

  constructor(private formBuilder: FormBuilder, private formBuilder2: FormBuilder, private greenhouseService: GreenhouseService) {
    this.commandForm = this.formBuilder.group({
      greenHouseID: ['', Validators.required],
      commandValue: ['', Validators.required]
    })
    this.regulatorForm = this.formBuilder2.group({
      greenHouseID2: ['', Validators.required]
    })
  }

  ngOnInit() {
  }

  onSelect(greenhouseid:number){
    this.greenhouseService.getGreenhouse(greenhouseid).subscribe(greenhouse=>this.selectedGreenhouse=greenhouse);
  }

  checkFields() {
    var tempGID = (<HTMLInputElement>document.getElementById("formGroupGreenhouseID")).value;
    var greenHouseIDValue = +tempGID;
    var selectedType = (<HTMLInputElement>document.getElementById("commandType")).value;
    var formValue = this.commandForm.controls.commandValue.value;

    this.responseMessageReg = "";

    if((tempGID == "" || (greenHouseIDValue < 0))) {
      console.log(tempGID);
      this.responseMessage = "Please pick an greenhouse or write a valid id.";
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
    console.log("GreenhouseID: " + greenHouseIDValue + ", Selected type: " + selectedType + ", Selected type number: "
                + this.selectedTypeNumber + ", Form value: " + formValue);
    this.responseMessage = "You send an command to the greenhouse.";
    return true;
  }

  startRegulator() {
    var tempGID = (<HTMLInputElement>document.getElementById("formGroupGreenhouseID2")).value;
    var greenhouseID2Value = + tempGID;

    this.responseMessage = "";

    if (tempGID == "" || (greenhouseID2Value < 0)) {
      this.responseMessageReg = "Please pick an greenhouse or write a valid id.";
      return false;
    }

    this.doRestCall();
    console.log(tempGID);
    this.responseMessageReg = "You started the regulator.";
    return true;
  }

  doRestCall() {
    console.log("REST CALL");
    return true;
  }
}
