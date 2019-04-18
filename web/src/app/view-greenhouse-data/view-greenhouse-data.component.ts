import { Component, OnInit, Input} from '@angular/core';
import { Greenhouse } from 'src/greenhouse';
import { GreenhouseService } from '../greenhouse.service';
import { FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-view-greenhouse-data',
  templateUrl: './view-greenhouse-data.component.html',
  styleUrls: ['./view-greenhouse-data.component.css']
})
export class ViewGreenhouseDataComponent implements OnInit {

  public selectedGreenhouse:Greenhouse;
  @Input() selectedID: number;

  constructor(private greenhouseService: GreenhouseService) { }

  ngOnInit() {
  }

  onSelect(greenhouseid:number){
    this.greenhouseService.getGreenhouse(greenhouseid).subscribe(greenhouse=>this.selectedGreenhouse=greenhouse);
  }

  
}
