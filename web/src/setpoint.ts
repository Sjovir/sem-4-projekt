export class Setpoint{
  lightSetpoints: { dateCreated: string, greenhouseID: number, red: number, blue: number, startTime: string }[];
  temperatureSetpoint: number;
  humiditySetpoint: number;

  // get lightSetPoints():[] {
  //   return this.lightSetpoints();
  // }

}
