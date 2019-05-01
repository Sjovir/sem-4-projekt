import { Component, OnInit, ViewChild } from '@angular/core';
import { GreenhouseCommandComponent } from '../greenhouse-command/greenhouse-command.component';

@Component({
  selector: 'app-greenhouse-management',
  templateUrl: './greenhouse-management.component.html',
  styleUrls: ['./greenhouse-management.component.css']
})
export class GreenhouseManagementComponent implements OnInit {

  @ViewChild(GreenhouseCommandComponent)
  private viewDataTables:GreenhouseCommandComponent;
  constructor() { }

  ngOnInit() {
  }

  receiveGreenhouseID($event){
    this.viewDataTables.onSelect($event);
  }

}
