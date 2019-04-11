package org.grp2.gms.api;

import io.javalin.Context;
import org.grp2.gms.common.CollectedData;
import org.grp2.gms.domain.*;

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
        context.json("GreenhouesID: " + id + ", Time Stamp: " + timeStamp + ", temperature " + temperature +
                ", humidity " + humidity + ", Red Light " + redLight + ", Blue Light " + blueLight);
    }

    public void getGreenhouseData(Context context) {
        gms.getGreenhouseData();

        context.status(200);
    }

    public void setupGreenhouse(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        String ipAddress = context.pathParam("ip-address");
        int port = Integer.parseInt(context.pathParam("port"));
        String name = context.pathParam("name");
        String location = context.pathParam("location");
        Greenhouse temp =  new Greenhouse(id, ipAddress, port, name, location);
        boolean success = gms.setupGreenhouse(temp);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void writeValue(Context context) {
        //String url = "http://localhost:7000/api/write-value/:type/:value";

    }

    public void writeGMSConnection(Context context) {

    }

    public void writeHumiditySetPoint(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        HumiditySetPoint humiditySetPoint = new HumiditySetPoint(id, minValue, maxValue, alarmMinValue, alarmMaxValue);
        boolean success = gms.setHumiditySetPoint(id, humiditySetPoint);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void writeTemperatureSetPoint(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        TemperatureSetPoint temperatureSetPoint = new TemperatureSetPoint(id, minValue, maxValue, alarmMinValue, alarmMaxValue);
        boolean success = gms.setTemperatureSetPoint(id, temperatureSetPoint);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void writeLightSetPoint(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        double blueValue = Double.parseDouble(context.pathParam("blue-value"));
        double redValue = Double.parseDouble(context.pathParam("red-value"));
        String time = context.pathParam("time");

        LightSetPoint lightSetPoint = new LightSetPoint(id, blueValue, redValue, time);
        boolean success = gms.addLightSetPoint(id, lightSetPoint);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void startRegulator(Context context) {

    }

}
