package org.grp2.gms.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import org.grp2.gms.common.CollectedData;
import org.grp2.gms.common.GreenhouseDTO;
import org.grp2.gms.domain.GMS;
import org.grp2.gms.domain.Greenhouse;

import java.util.HashMap;
import java.util.Map;

public class APIHandler {
    private GMS gms;

    public APIHandler(GMS gms) {
        this.gms = gms;
    }


    public void writeCollectedData(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));
        String timeStamp = context.pathParam("timestamp");
        double temperature = Double.parseDouble(context.pathParam("temperature"));
        double humidity = Double.parseDouble(context.pathParam("humidity"));
        int redLight = Integer.parseInt(context.pathParam("red-light"));
        int blueLight = Integer.parseInt(context.pathParam("blue-light"));

        CollectedData collectedData = new CollectedData(id, timeStamp, temperature, humidity, redLight, blueLight);
        gms.writeCollectedData(collectedData);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();



        try {
            context.json(mapper.writeValueAsString(map));
            context.status(200);
        } catch (JsonProcessingException e) {
            context.status(500);
            e.printStackTrace();
        }
    }

    public void getGreenhouseData(Context context) {
        int id = Integer.parseInt(context.pathParam("greenhouse-id"));


        GreenhouseDTO data = gms.getGreenhouseData(id);

        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> map = new HashMap<String, Object>();
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
