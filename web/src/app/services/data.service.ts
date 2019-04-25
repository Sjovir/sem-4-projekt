import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

const gmsEndpoint = 'http://10.123.3.53:7001/api/';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }


 /**
  * Raspberry pi calls
  * @param greenhouseID
  * @param type
  * @param value
  */
 public writeValue(greenhouseID:number, type: number, value: number): Observable<any> {
  return this.http.post(gmsEndpoint + 'write-value/' + greenhouseID + '/' + type + '/' + value , null, httpOptions);
 }

 /**
  * Callback connection from gnode to gms
  * @param greenhouseID
  * @param ipAddress
  * @param port
  */
 public writeGmsConnection(greenhouseID: number, ipAddress: string, port: number): Observable<any> {
  return this.http.post(gmsEndpoint + 'write-gms-connection/' + greenhouseID + '/' + ipAddress + '/' + port, null, httpOptions);
 }

 /**
  * Send setpoint for humidity value
  * @param greenhouseID
  * @param minValue
  * @param maxValue
  * @param alarmMinValue
  * @param alarmMaxValue
  */
 public writeHumiditySetpoint(greenhouseID: number, minValue: number, maxValue: number, alarmMinValue: number, alarmMaxValue: number): Observable<any> {
  return this.http.post(gmsEndpoint + 'write-humidity-setpoint/' + greenhouseID + '/' + minValue + '/' + maxValue + '/' + alarmMinValue + '/' + alarmMaxValue, null, httpOptions );
 }

 /**
  *  send setpoint for temperature value
  * @param greenhouseID
  * @param minValue
  * @param maxValue
  * @param alarmMinValue
  * @param alarmMaxValue
  */
 public writeTemperatureSetpoint(greenhouseID: number, minValue: number, maxValue: number, alarmMinValue: number, alarmMaxValue: number): Observable<any> {
  return this.http.post(gmsEndpoint + 'write-temperature-setpoint/' + greenhouseID + '/' + minValue + '/' + maxValue + '/' + alarmMinValue + '/' + alarmMaxValue, null, httpOptions );
 }

 /**
  * send setpoint for light value
  * @param greenhouseID
  * @param blueLight
  * @param redLight
  * @param time
  */
 public writeLightSetpoint(greenhouseID: number, blueLight: number, redLight: number, time: string): Observable<any> {
  return this.http.post(gmsEndpoint + 'write-light-setpoint/' + greenhouseID + '/' + blueLight + '/' + redLight + '/' + time, null, httpOptions);
 }

 /**
  * send command for starting regulator
  * @param greenhouseID
  */
 public startRegulator(greenhouseID: number): Observable<any> {
  return this.http.post(gmsEndpoint + 'start-regulator/' + greenhouseID, null , httpOptions);
 }

 /**
  * gms get requests
  */

  public getGreenhouses(): Observable<any> {
    return this.http.get(gmsEndpoint + 'get-greenhouses', httpOptions);
  }

  public getGreenhouseSetpoints(greenhouseID: number): Observable<any> {
    return this.http.get(gmsEndpoint + 'get-greenhouse-setpoints/' + greenhouseID, httpOptions);
  }

  public getGreenhouseData(greenhouseID: number): Observable<any> {
    return this.http.get(gmsEndpoint + 'get-greenhouse-data/' + greenhouseID, httpOptions);
  }

  /**
   * gms post requests
   */

   public writeCollectedData(greenhouseID: number, timeStamp: string, temperature: number, humidity: number, redLight:number, blueLight:number): Observable<any> {
     return this.http.post(gmsEndpoint + 'write-collected-data/' + greenhouseID + '/' + timeStamp + '/' + temperature + '/' + humidity + '/' + redLight + '/' + blueLight, null,  httpOptions);
   }

   public setupGreenhouse(ipAddress: string, port: number, location: string, name: string): Observable<any> {
     console.log('called');
     return this.http.post(gmsEndpoint + 'setup-greenhouse/' + ipAddress + '/' + port + '/' + location + '/' + name, null, httpOptions);
   }

}
