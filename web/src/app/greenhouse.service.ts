import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
 
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { Greenhouse } from 'src/greenhouse';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class GreenhouseService {

  greenhouses: Greenhouse[]=[
    {id:1,
      name:"Beate",
      ipAddress:"localhost",
      port:5043,
      location:"Lige her",
      dateCreated:"idag",
      lightData:[
        {timeCollected:"igår",redValue:25,blueValue:25},
        {timeCollected:"igår",redValue:100,blueValue:34},
        {timeCollected:"igår",redValue:22,blueValue:21},
        {timeCollected:"igår",redValue:67,blueValue:43}
      ],
      temperatureData:[
        {value:34.5,timeCollected:"idag"},
        {value:32.5,timeCollected:"idag"},
        {value:36.5,timeCollected:"idag"},
        {value:14.5,timeCollected:"idag"}
      ],
      humidityData:[
        {value:32.5,timeCollected:"idag"},
        {value:35.5,timeCollected:"idag"},
        {value:31.5,timeCollected:"idag"},
        {value:20.5,timeCollected:"idag"}
      ]
    },
    {id:2,
      name:"Mulle",
      ipAddress:"localhost",
      port:5053,
      location:"Lige her",
      dateCreated:"idag",
      lightData:[
        {timeCollected:"imorgen",redValue:65,blueValue:15},
        {timeCollected:"imorgen",redValue:10,blueValue:84},
        {timeCollected:"imorgen",redValue:52,blueValue:28},
        {timeCollected:"imorgen",redValue:27,blueValue:44}
      ],
      temperatureData:[
        {value:34.5,timeCollected:"imorgen"},
        {value:32.5,timeCollected:"imorgen"},
        {value:36.5,timeCollected:"imorgen"},
        {value:14.5,timeCollected:"imorgen"}
      ],
      humidityData:[
        {value:32.5,timeCollected:"imorgen"},
        {value:35.5,timeCollected:"imorgen"},
        {value:31.5,timeCollected:"imorgen"},
        {value:20.5,timeCollected:"imorgen"}
      ]
    },
    {id:3,
      name:"Test",
      ipAddress:"localhost",
      port:5053,
      location:"Lige nu",
      dateCreated:"the day",
      lightData:[
        {timeCollected:"en eller anden dag",redValue:65,blueValue:15},
        {timeCollected:"en eller anden dag",redValue:10,blueValue:84},
        {timeCollected:"en eller anden dag",redValue:52,blueValue:28},
        {timeCollected:"en eller anden dag",redValue:27,blueValue:44}
      ],
      temperatureData:[
        {value:34.5,timeCollected:"en eller anden dag"},
        {value:32.5,timeCollected:"en eller anden dag"},
        {value:36.5,timeCollected:"en eller anden dag"},
        {value:14.5,timeCollected:"en eller anden dag"}
      ],
      humidityData:[
        {value:32.5,timeCollected:"en eller anden dag"},
        {value:35.5,timeCollected:"en eller anden dag"},
        {value:31.5,timeCollected:"en eller anden dag"},
        {value:20.5,timeCollected:"en eller anden dag"}
      ]
    }
  ];
 
  constructor(){}

  getGreenhouse(id: number):Observable<Greenhouse>{
    return of(this.greenhouses.find(greenhouse=>greenhouse.id==id));
  }
  getGreenhouses():Observable<Greenhouse[]>{
    return of(this.greenhouses)
  }

}
