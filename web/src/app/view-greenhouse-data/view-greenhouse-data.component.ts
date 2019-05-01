import { Component, OnInit} from '@angular/core';
import { DataService } from '../services/data.service';
import { Greenhouse } from 'src/greenhouse';

@Component({
  selector: 'app-view-greenhouse-data',
  templateUrl: './view-greenhouse-data.component.html',
  styleUrls: ['./view-greenhouse-data.component.css']
})
export class ViewGreenhouseDataComponent implements OnInit {

  public selectedGreenhouse:Greenhouse;
  constructor(private greenhouseService: DataService) { }

  ngOnInit() {
  }

  onSelect(greenhouseid:number){
    this.greenhouseService.getGreenhouseData(greenhouseid).subscribe(greenhouse=>{
      this.selectedGreenhouse=JSON.parse(greenhouse);
    });
  }
}
