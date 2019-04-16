package org.grp2.gms.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import org.grp2.gms.common.*;
import org.grp2.gms.domain.*;

import java.util.HashMap;
import java.util.Map;

public class APIHandler {
    private GMS gms;

    public APIHandler(GMS gms) {
        this.gms = gms;
    }


    public void writeCollectedData(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        long timeStamp = Long.parseLong(context.pathParam("timestamp"));
        double temperature = Double.parseDouble(context.pathParam("temperature"));
        double humidity = Double.parseDouble(context.pathParam("humidity"));
        int redLight = Integer.parseInt(context.pathParam("red-light"));
        int blueLight = Integer.parseInt(context.pathParam("blue-light"));

        LightDTO lightDTO = new LightDTO(timeStamp,redLight,blueLight);
        HumidityDTO humidityDTO = new HumidityDTO(timeStamp,humidity);
        TemperatureDTO temperatureDTO = new TemperatureDTO(timeStamp,temperature);

        if(gms.writeCollectedData(id, lightDTO,humidityDTO,temperatureDTO)) {
            context.status(200);
        }else{
            context.status(500);
        }
    }

    public void getGreenhouseData(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));

        GreenhouseDTO data = gms.getGreenhouseData(id);

        ObjectMapper mapper = new ObjectMapper();

        try {
            context.json(mapper.writeValueAsString(data));
            context.status(200);
        } catch (JsonProcessingException e) {
            context.status(500);
            e.printStackTrace();
        }

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
        int id = Integer.parseInt(context.pathParam("green-house-id"));
        String ipAddress = context.pathParam("ip-address");
        int port = Integer.parseInt(context.pathParam("port"));

        if(gms.setGMSConnectionOnGreenhouse(id,port,ipAddress)){
            context.status(200);
        }else{
            context.status(500);
        }

    }

    public void writeHumiditySetPoint(Context context) {
        long dateCreated = System.currentTimeMillis();
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        HumiditySetpointDTO humiditySetPointDTO = new HumiditySetpointDTO(dateCreated, id, minValue, maxValue, alarmMinValue, alarmMaxValue);
        boolean success = gms.setHumiditySetPoint(id, humiditySetPointDTO);

        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void writeTemperatureSetPoint(Context context) {
        long dateCreated = System.currentTimeMillis();
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        TemperatureSetpointDTO temperatureSetPointDTO = new TemperatureSetpointDTO(dateCreated, id, minValue, maxValue, alarmMinValue, alarmMaxValue);
        boolean success = gms.setTemperatureSetPoint(id, temperatureSetPointDTO);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void writeLightSetPoint(Context context) {
        long dateCreated = System.currentTimeMillis();
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        int blueValue = Integer.parseInt(context.pathParam("blue-value"));
        int redValue = Integer.parseInt(context.pathParam("red-value"));
        String time = context.pathParam("time");

        LightSetpointDTO lightSetPointDTO = new LightSetpointDTO(dateCreated, id, blueValue, redValue, time);
        boolean success = gms.addLightSetPoint(id, lightSetPointDTO);
        if (success) {
            context.status(200);
        } else {
            context.status(500);
        }
    }

    public void startRegulator(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        if(gms.startRegulator(id)){
            context.status(200);
        }else{
            context.status(500);
        }

    }

}
