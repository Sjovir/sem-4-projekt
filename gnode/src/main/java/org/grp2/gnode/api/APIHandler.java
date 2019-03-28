package org.grp2.gnode.api;
import io.javalin.Context;
import org.grp2.gnode.domain.GNode;


public class APIHandler {
    private GNode gnode;

    public APIHandler(GNode gnode) {
        this.gnode = gnode;
    }

    public void readValue(Context context) {
        int type = Integer.parseInt(context.pathParam("type"));
        int value = gnode.readValue(type);

        context.json(value);

    }

    public void writeValue(Context context){
        int type = Integer.parseInt(context.pathParam("type"));
        double value = Double.parseDouble(context.pathParam("value"));
        gnode.writeValue(type, value);
        context.status(200);
    }

    public void writeGMSCollection(Context context) {
        String url = context.pathParam("url");
        int port = Integer.parseInt(context.pathParam("port"));
        int greenHouseID = Integer.parseInt(context.pathParam("green-house-id"));
        gnode.setGMSCollection(url, port, greenHouseID);
        context.status(200);
    }

    public void writeHumiditySetPoint(Context context) {
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setHumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
    }

    public void writeTemperatureSetPoint(Context context) {
        double minValue = Double.parseDouble(context.pathParam("min-value"));
        double maxValue = Double.parseDouble(context.pathParam("max-value"));
        double alarmMinValue = Double.parseDouble(context.pathParam("alarm-min-value"));
        double alarmMaxValue = Double.parseDouble(context.pathParam("alarm-max-value"));

        gnode.setTemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        context.status(200);
    }

    public void writeLightSetPoint(Context context) {
        double blueValue = Double.parseDouble(context.pathParam("blue-value"));
        double redValue = Double.parseDouble(context.pathParam("red-value"));
        String time = context.pathParam("time");

        gnode.setLightSetPoint(blueValue, redValue, time);
        context.status(200);
    }

    public void startRegulator(Context context) {
        gnode.startRegulator();
        context.status(200);
    }
}
