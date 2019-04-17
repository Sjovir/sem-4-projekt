import { Component, OnInit } from '@angular/core';
import { Greenhouse } from 'src/greenhouse';
import { GreenhouseService } from '../greenhouse.service';

@Component({
  selector: 'app-view-greenhouse-data',
  templateUrl: './view-greenhouse-data.component.html',
  styleUrls: ['./view-greenhouse-data.component.css']
})
export class ViewGreenhouseDataComponent implements OnInit {

  public selectedGreenhouse:Greenhouse;

  constructor(private greenhouseService: GreenhouseService) { }

  ngOnInit() {

  }

  onSelect(greenhouseid:number){
    this.greenhouseService.getGreenhouse(greenhouseid).subscribe(greenhouse=>this.selectedGreenhouse=greenhouse);
  }

}
