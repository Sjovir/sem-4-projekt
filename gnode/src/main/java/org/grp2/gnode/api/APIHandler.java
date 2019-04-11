package org.grp2.gnode.api;
import io.javalin.Context;
import org.grp2.gnode.domain.GNode;


public class APIHandler {
    private GNode gnode;

    public APIHandler(GNode gnode) {
        this.gnode = gnode;
    }

    public void writeValue(Context context){
        int type = Integer.parseInt(context.pathParam("type"));
        double value = Double.parseDouble(context.pathParam("value"));

        boolean success = gnode.writeValue(type, value);
        if(success){
            context.status(200);
        }else{
            context.status(408);
        }
        context.json("Perform greenhouse action - Action id: " + type + ", Value written: " + value);
    }

    public void writeGMSConnection(Context context) {
        int port = Integer.parseInt(context.pathParam("port"));
        String url = context.pathParam("url");
        int greenhouseID = Integer.parseInt(context.pathParam("green-house-id"));

        boolean success =gnode.setGMSConnection(port, url, greenhouseID);

        if(success){
            context.status(200);
        }else{
            context.status(404);
        }
        context.json("GMS Connection - Port: " + port + ", URL: " + url + ", greenhouseID: " + greenhouseID);
    }

    public void writeHumiditySetPoint(Context context) {
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setHumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
        context.json("Set humidity setpoint - min: " + minValue + ", max: " + maxValue + ", alarm-min: " + alarmMinValue + ", alarm-max: " + alarmMaxValue);
    }

    public void writeTemperatureSetPoint(Context context) {
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setTemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
        context.json("Set temperature setpoint - min: " + minValue + ", max: " + maxValue + ", alarm-min: " + alarmMinValue + ", alarm-max: " + alarmMaxValue);
    }

    public void writeLightSetPoint(Context context) {
        System.out.println("it worked tho");
        double blueValue = Double.parseDouble(context.pathParam("blue-value"));
        double redValue = Double.parseDouble(context.pathParam("red-value"));
        String time = context.pathParam("time");

        gnode.setLightSetPoint(blueValue, redValue, time);
        context.status(200);

        context.json("Add light setpoint - blue: " + blueValue + ", red: " + redValue + ", time: " + time);
    }

    public void startRegulator(Context context) {
        gnode.startRegulator();
        context.status(200);
        context.json("Start regulator");
    }
}
