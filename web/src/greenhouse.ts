export class Greenhouse{
    id: number;
    name: string;
    ipAddress: string;
    port: number;
    location: string;
    dateCreated: string;
    lightData: {timeCollected:string,redValue:number,blueValue:number}[];
    humidityData: {timeCollected:string,value:number}[];
    temperatureData:{timeCollected:string,value:number}[];
}