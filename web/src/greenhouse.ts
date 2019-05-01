export class Greenhouse{
    id: number;
    name: string;
    ipAddress: string;
    port: number;
    location: string;
    dateCreated: string;
    light: {timeCollected:string,redValue:number,blueValue:number}[];
    humidity: {timeCollected:string,value:number}[];
    temperature:{timeCollected:string,value:number}[];
}