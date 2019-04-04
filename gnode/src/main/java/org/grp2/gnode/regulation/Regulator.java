package org.grp2.gnode.regulation;

import org.grp2.gnode.hardware.GreenhouseController;

import java.util.ArrayList;

public class Regulator {

    private GreenhouseController greenhouseController;

    private HumiditySetPoint humiditySetPoint;
    private TemperatureSetPoint temperatureSetPoint;
    private ArrayList<LightSetPoint> lightSetPointArray = new ArrayList<>();
    private boolean automationActive;

    public Regulator(GreenhouseController greenhouseController) {
        this.greenhouseController = greenhouseController;
    }

    public void start() {
        automationActive = true;
        //postman test
        System.out.print("START WORKS");
    }

    public void stop() {
        automationActive = false;
        //postman test
        System.out.println("STOP WORKS");
    }

    public void addHumiditySetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        humiditySetPoint = new HumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);

        //postman test
        System.out.println(" " + minValue + " " + maxValue + " " + alarmMinValue + " " + alarmMaxValue);
    }

    public void addTemperatureSetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        temperatureSetPoint = new TemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);

        //postman test
        System.out.println(" " + minValue + " " + maxValue + " " + alarmMinValue + " " + alarmMaxValue);
    }

    public void addLightSetPoint(double blueValue, double redValue, String time) {
        LightSetPoint lightSetPoint = new LightSetPoint(blueValue, redValue, time);
        lightSetPointArray.add(lightSetPoint);

        //postman test
        System.out.print(" " + blueValue + " " + redValue + " " + time);
    }

    public boolean isAutomationActive() {
        return automationActive;
    }

    public void setAutomationActive(boolean automationActive) {
        this.automationActive = automationActive;
    }
}
