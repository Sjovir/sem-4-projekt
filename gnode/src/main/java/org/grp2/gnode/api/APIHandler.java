package org.grp2.gnode.api;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import org.grp2.gnode.domain.GNode;

import java.util.HashMap;
import java.util.Map;


public class APIHandler {
    private GNode gnode;

    public APIHandler(GNode gnode) {
        this.gnode = gnode;
    }

    public void writeValue(Context context) throws JsonProcessingException {
        System.out.println("Route called: write-value");

        int type = Integer.parseInt(context.pathParam("type"));
        double value = Double.parseDouble(context.pathParam("value"));

        boolean success = gnode.writeValue(type, value);
        if(success){
            context.status(200);
        }else{
            context.status(408);
        }
    }

    public void writeGMSConnection(Context context) {
        System.out.println("Route called: write-gms-connection");

        int port = Integer.parseInt(context.pathParam("port"));
        String url = context.pathParam("url");
        int greenhouseID = Integer.parseInt(context.pathParam("green-house-id"));

        boolean success =gnode.setGMSConnection(port, url, greenhouseID);

        if(success){
            context.status(200);
        }else{
            context.status(404);
        }
    }

    public void writeHumiditySetPoint(Context context) {
        System.out.println("Route called: write-humidity-setpoint");

        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setHumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
    }

    public void writeTemperatureSetPoint(Context context) {
        System.out.println("Route called: write-temperature-setpoint");

        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setTemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
    }

    public void writeLightSetPoint(Context context) {
        System.out.println("Route called: write-light-setpoint");

        double blueValue = Double.parseDouble(context.pathParam("blue-value"));
        double redValue = Double.parseDouble(context.pathParam("red-value"));
        String time = context.pathParam("time");

        gnode.setLightSetPoint(blueValue, redValue, time);
        context.status(200);
    }

    public void startRegulator(Context context) {
        System.out.println("Route called: start-regulator");

        gnode.startRegulator();
        context.status(200);
    }
}
