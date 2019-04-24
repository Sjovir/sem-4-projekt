import { Component, OnInit,ViewChild } from '@angular/core';
import { ViewGreenhouseDataComponent } from '../view-greenhouse-data/view-greenhouse-data.component';


@Component({
  selector: 'app-view-data',
  templateUrl: './view-data.component.html',
  styleUrls: ['./view-data.component.css']
})
export class ViewDataComponent implements OnInit{

  @ViewChild(ViewGreenhouseDataComponent)
  private viewDataTables:ViewGreenhouseDataComponent;
  constructor() { }

  ngOnInit() {
  }

  receiveGreenhouseID($event){
    this.viewDataTables.onSelect($event);
  }
}
