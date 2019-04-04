package org.grp2.gms.api;

import io.javalin.Context;
import org.grp2.gms.common.CollectedData;
import org.grp2.gms.domain.GMS;
import org.grp2.gms.domain.Greenhouse;

public class APIHandler {
    private GMS gms;

    public APIHandler(GMS gms) {
        this.gms = gms;
    }


    public void writeCollectedData(Context context){
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        String timeStamp = context.pathParam("timestamp");
        double temperature = Double.parseDouble(context.pathParam("temperature"));
        double humidity = Double.parseDouble(context.pathParam("humidity"));
        int redLight = Integer.parseInt(context.pathParam("red-light"));
        int blueLight = Integer.parseInt(context.pathParam("blue-light"));

        CollectedData collectedData = new CollectedData(id, timeStamp, temperature, humidity, redLight, blueLight);
        gms.writeCollectedData(collectedData);

        context.status(200);
    }

    public void getGreenhouseData(Context context) {
        gms.getGreenhouseData();

        context.status(200);
    }

    public void setupGreenhouse(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        String ipAddress = context.pathParam("ip-address");
        String name = context.pathParam("name");
        String location = context.pathParam("location");
        Greenhouse temp =  new Greenhouse(id, ipAddress, name, location);
        gms.setupGreenhouse(temp);

        context.status(200);
    }

    public void writeValue(Context context) {
        //String url = "http://localhost:7000/api/write-value/:type/:value";

    }

    public void writeGMSConnection(Context context) {

    }

    public void writeHumiditySetPoint(Context context) {

    }

    public void writeTemperatureSetPoint(Context context) {

    }

    public void writeLightSetPoint(Context context) {

    }

    public void startRegulator(Context context) {

    }

}
