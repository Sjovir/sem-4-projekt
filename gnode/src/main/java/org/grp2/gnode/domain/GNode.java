package org.grp2.gnode.domain;

import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;
import org.grp2.gnode.regulation.Regulator;

public class GNode {
    GreenhouseController greenhouseController;
    Regulator regulator;

    public GNode() {
        greenhouseController = new GreenhouseController(5000, "192.168.0.40");
        regulator = new Regulator();
    }

    public void writeValue(int id, double value) {
        regulator.stop();

        Action writeAction = Action.getActionFromID(id);

        boolean answer = greenhouseController.writeValue(writeAction, value);

        //postman test
        System.out.println("value: " + value + " answer: " + answer);
    }

    public void setGMSConnection(int gmsPORT, String gmsURL, int greenHouseID) {
        //postman test
        System.out.println("url: " + gmsURL);
        System.out.println("port: " + gmsPORT);
        System.out.println("ghid: " + greenHouseID);
    }

    public void setHumiditySetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        regulator.addHumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
    }

    public void setTemperatureSetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        regulator.addTemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
    }

    public void setLightSetPoint(double blueValue, double redValue, String time) {
        regulator.addLightSetPoint(blueValue, redValue, time);
    }

    public void startRegulator() {
        regulator.start();
    }
}
