package org.grp2.gnode.domain;

import org.grp2.gnode.regulation.Regulator;

public class GNode {
    Regulator regulator = new Regulator();

    public GNode() {
    }

    public void writeValue(int id, double value) {
        regulator.stop();
        //postman test
        System.out.println("value: " + value);
    }

    public void setGMSCollection(String gmsURL, int gmsPORT, int greenHouseID) {
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
