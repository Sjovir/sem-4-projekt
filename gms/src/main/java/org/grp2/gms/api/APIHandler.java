package org.grp2.gms.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.Context;
import org.grp2.gms.common.*;
import org.grp2.gms.domain.GMS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class APIHandler {
    private GMS gms;

    public APIHandler(GMS gms) {
        this.gms = gms;
    }

    public void writeValue(Context context) {
        System.out.println("Route called: write-value");

        try {
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            int type = Integer.parseInt(context.pathParam("type"));
            double value = Double.parseDouble(context.pathParam("value"));

            boolean success = gms.writeValue(id, type, value);
            if (success)
                context.status(200);
            else
                context.status(408);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void writeGMSConnection(Context context) {
        System.out.println("Route called: write-gms-connection");

        try {
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            String ipAddress = context.pathParam("ip-address");
            int port = Integer.parseInt(context.pathParam("port"));

            boolean success = gms.setGMSConnectionOnGreenhouse(id, port, ipAddress);
            if(success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void writeHumiditySetPoint(Context context) {
        System.out.println("Route called: write-humidity-setpoint");

        try {
            long dateCreated = System.currentTimeMillis();
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            double minValue = Double.parseDouble(context.pathParam("min-value"));
            double maxValue = Double.parseDouble(context.pathParam("max-value"));
            double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
            double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

            HumiditySetpointDTO humiditySetPointDTO = new HumiditySetpointDTO(dateCreated, id, minValue, maxValue, alarmMinValue, alarmMaxValue);

            boolean success = gms.setHumiditySetPoint(id, humiditySetPointDTO);
            if (success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void writeTemperatureSetPoint(Context context) {
        System.out.println("Route called: write-temperature-setpoint");

        try {
            long dateCreated = System.currentTimeMillis();
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            double minValue = Double.parseDouble(context.pathParam("min-value"));
            double maxValue = Double.parseDouble(context.pathParam("max-value"));
            double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
            double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

            TemperatureSetpointDTO temperatureSetPointDTO = new TemperatureSetpointDTO(dateCreated, id, minValue, maxValue, alarmMinValue, alarmMaxValue);

            boolean success = gms.setTemperatureSetPoint(id, temperatureSetPointDTO);
            if (success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void writeLightSetPoint(Context context) {
        System.out.println("Route called: write-light-setpoint");

        try {
            long dateCreated = System.currentTimeMillis();
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            int blueValue = Integer.parseInt(context.pathParam("blue-value"));
            int redValue = Integer.parseInt(context.pathParam("red-value"));
            String time = context.pathParam("time");

            if (time.length() > 5)
                throw new NumberFormatException("Too many characters in start time parameter: " + time);

            LightSetpointDTO lightSetPointDTO = new LightSetpointDTO(dateCreated, id, blueValue, redValue, time);

            boolean success = gms.addLightSetPoint(id, lightSetPointDTO);
            if (success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void startRegulator(Context context) {
        System.out.println("Route called: start-regulator");

        try {
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));

            boolean success = gms.startRegulator(id);
            if(success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void getGreenhouses(Context context) {
        System.out.println("Route called: get-greenhouses");

        List<GreenhouseDTO> greenhouses = gms.getGreenhouses();

        ObjectMapper mapper = new ObjectMapper();

        if (greenhouses != null) {
            try {
                context.json(mapper.writeValueAsString(greenhouses));
                context.status(200);
            } catch (JsonProcessingException e) {
                context.status(500);
                e.printStackTrace();
            }
        } else
            context.status(500);
    }

    public void getGreenhouseSetpoints(Context context) {
        System.out.println("Route called: get-greenhouse-setpoints");

        try {
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));

            ObjectMapper mapper = new ObjectMapper();

            HumiditySetpointDTO humiditySetpointDTO = gms.getHumiditySetpoint(id);
            TemperatureSetpointDTO temperatureSetpointDTO = gms.getTemperatureSetpoint(id);
            List<LightSetpointDTO> lightSetpointDTO = gms.getLightSetpoints(id);

            Map<String, Object> setpoints = new HashMap<>();

            setpoints.put("humiditySetpoint", humiditySetpointDTO);
            setpoints.put("temperatureSetpoint", temperatureSetpointDTO);
            setpoints.put("lightSetpoints", lightSetpointDTO);

            try {
                context.json(mapper.writeValueAsString(setpoints));
                context.status(200);
            }catch (JsonProcessingException e) {
                context.status(500);
                e.printStackTrace();
            }

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void getGreenhouseData(Context context) {
        System.out.println("Route called: get-greenhouse-data");

        try {
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

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }


    public void writeCollectedData(Context context) {
        System.out.println("Route called: write-collected-data");

        try {
            int id = Integer.parseInt(context.pathParam("greenhouse-id"));
            long timeStamp = Long.parseLong(context.pathParam("timestamp"));
            double temperature = Double.parseDouble(context.pathParam("temperature"));
            double humidity = Double.parseDouble(context.pathParam("humidity"));
            int redLight = Integer.parseInt(context.pathParam("red-light"));
            int blueLight = Integer.parseInt(context.pathParam("blue-light"));

            LightDTO lightDTO = new LightDTO(timeStamp, redLight, blueLight);
            HumidityDTO humidityDTO = new HumidityDTO(timeStamp, humidity);
            TemperatureDTO temperatureDTO = new TemperatureDTO(timeStamp, temperature);

            boolean success = gms.writeCollectedData(id, lightDTO,humidityDTO,temperatureDTO);
            if (success)
                context.status(200);
            else
                context.status(500);


        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }

    public void setupGreenhouse(Context context) {
        System.out.println("Route called: setup-greenhouse");

        try {
            String ipAddress = context.pathParam("ip-address");
            int port = Integer.parseInt(context.pathParam("port"));
            String name = context.pathParam("name");
            String location = context.pathParam("location");
            Long dateCreated = System.currentTimeMillis();

            GreenhouseDTO greenhouseDTO =  new GreenhouseDTO(ipAddress, port, location, name, dateCreated);

            boolean success = gms.setupGreenhouse(greenhouseDTO);
            if (success)
                context.status(200);
            else
                context.status(500);

        } catch (NumberFormatException e) {
            context.status(400);
            e.printStackTrace();
        }
    }
}
