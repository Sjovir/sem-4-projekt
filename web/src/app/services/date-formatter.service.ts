import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DateFormatterService {

  constructor() { }


  public epochToReadable(epoch:number):string{
    var date = new Date(epoch);
    var res:string="";
    res+=this.addZero(date.getUTCDate())+"/"+this.addZero(date.getUTCMonth())+"-"+date.getUTCFullYear();
    res+=" ";
    res+=this.addZero(date.getUTCHours())+":"+this.addZero(date.getUTCMinutes())+":"+this.addZero(date.getUTCSeconds()).toString();

    return res;
  }
  public addZero(i){
    if (i < 10) {
      i = "0" + i;
    }
    return i;
  }
}
