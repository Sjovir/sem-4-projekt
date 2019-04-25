package org.grp2.gnode.regulation;

import org.grp2.gnode.hardware.GreenhouseController;

import java.util.ArrayList;
import java.util.List;

public class Regulator {

    private GreenhouseController greenhouseController;

    private HumiditySetPoint humiditySetpoint;
    private TemperatureSetPoint temperatureSetpoint;
    private List<LightSetPoint> lightSetPointList = new ArrayList<>();
    private boolean automationActive;

    public Regulator(GreenhouseController greenhouseController) {
        this.greenhouseController = greenhouseController;
    }

    public void start() {
        automationActive = true;
        //postman test
        System.out.print("START REGULATOR");
    }

    public void stop() {
        automationActive = false;
        //postman test
        System.out.println("STOP REGULATOR");
    }

    public void addHumiditySetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        humiditySetpoint = new HumiditySetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
    }

    public void addTemperatureSetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        temperatureSetpoint = new TemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
    }

    public void addLightSetPoint(double blueValue, double redValue, String time) {
        LightSetPoint newLightSetpoint = new LightSetPoint(blueValue, redValue, time);
        
        for (LightSetPoint lightSetpoint : lightSetPointList) {
            if (lightSetpoint.time == time) {
                lightSetPointList.remove(lightSetpoint);
                break;
            }
        }
        
        lightSetPointList.add(newLightSetpoint);
    }

    public boolean isAutomationActive() {
        return automationActive;
    }

    public void setAutomationActive(boolean automationActive) {
        this.automationActive = automationActive;
    }
}
