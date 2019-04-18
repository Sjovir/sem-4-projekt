import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Greenhouse } from 'src/greenhouse';
import { GreenhouseService } from '../greenhouse.service';

@Component({
  selector: 'app-greenhouse-list',
  templateUrl: './greenhouse-list.component.html',
  styleUrls: ['./greenhouse-list.component.css']
})
export class GreenhouseListComponent implements OnInit {

  greenhouses:Greenhouse[];
  @Output() selectedEvent=new EventEmitter<number>();

  constructor(private greenhouseService:GreenhouseService) { }

  ngOnInit() {    
    this.greenhouseService.getGreenhouses().subscribe(greenhouses=>this.greenhouses=greenhouses)
  }

  // selectGreenhouse(id:number){
  //   alert("Greenhouse"+id+"selected!")
  // }

  selectGreenhouse(id:number){
    this.selectedEvent.emit(id);
  }

}
