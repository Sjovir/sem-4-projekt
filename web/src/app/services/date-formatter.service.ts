import { Injectable } from '@angular/core';
import { stringify } from '@angular/compiler/src/util';

@Injectable({
  providedIn: 'root'
})
export class DateFormatterService {

  constructor() { }


  public epochToReadable(epoch:number):string{
    var date = new Date(epoch);
    var res:string="";
    res+=date.getUTCDate().toString()+"/"+date.getUTCMonth().toString()+"-"+date.getUTCFullYear().toString();
    res+=" ";
    res+=date.getUTCHours().toString()+":"+date.getUTCMinutes().toString()+":"+date.getUTCSeconds().toString();

    return res;
  }
  public addZero(i:number) :string {
    if (i < 10) {
      i = "0" + i;
    }
    return i.toString();
  }
}
