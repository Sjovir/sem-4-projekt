package org.grp2.gnode.regulation;

import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;

import java.text.SimpleDateFormat;
import java.util.*;

public class Regulator implements Runnable {

    private GreenhouseController greenhouseController;

    private HumiditySetPoint humiditySetpoint;
    private TemperatureSetPoint temperatureSetpoint;
    private List<LightSetPoint> lightSetPointList = new ArrayList<>();
    private boolean automationActive;

    private int humidityInterval = 10;
    private int temperatureInterval = 5;
    private int intervalTime;

    private int lastHumidity
    private LightSetPoint currentLightSetpoint;

    public Regulator(GreenhouseController greenhouseController) {
        this.greenhouseController = greenhouseController;

        this.addLightSetPoint(50, 50, "07:50");
        this.addLightSetPoint(50, 50, "08:50");
        this.addLightSetPoint(50, 50, "00:50");
        this.addLightSetPoint(50, 50, "09:50");
        this.addLightSetPoint(50, 50, "06:50");
        this.addLightSetPoint(50, 50, "10:09");
        this.addLightSetPoint(50, 50, "10:50");
        this.addLightSetPoint(50, 50, "11:20");
        this.addLightSetPoint(50, 50, "10:43");

        this.addLightSetPoint(50, 50, "10:41");

        this.addLightSetPoint(50, 50, "10:13");
    }

    public void start() {
        automationActive = true;
        //postman test
        System.out.println("START REGULATOR");
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
            if (lightSetpoint.getTime().equals(time)) {
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

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (automationActive)
                regulate();
        }
    }
    
    private void regulate () {

        if (intervalTime % humidityInterval  == 0) {
            regulateHumdity();
        }

        if (intervalTime % temperatureInterval == 0) {
            regulateTemperature();
        }

        regulateLight();

        intervalTime++;
    }

    private void regulateHumdity() {

    }

    private void regulateTemperature() {

    }

    private void regulateLight() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(new Date());

        List<LightSetPoint> setPointsBeforeTime = new ArrayList<>();

        for (LightSetPoint lightSetPoint : lightSetPointList)
            if (currentTime.compareTo(lightSetPoint.getTime()) >= 0)
                setPointsBeforeTime.add(lightSetPoint);

        setPointsBeforeTime.sort(Comparator.comparing(LightSetPoint::getTime));

//        System.out.println("Latest time: " + setPointsBeforeTime.get(setPointsBeforeTime.size() - 1).getTime());

        if (currentLightSetpoint != setPointsBeforeTime.get(setPointsBeforeTime.size() - 1))
        {
            currentLightSetpoint = setPointsBeforeTime.get(setPointsBeforeTime.size() - 1);
            greenhouseController.writeValue(Action.WRITE_BLUE_LIGHT, currentLightSetpoint.getBlueValue());
            greenhouseController.writeValue(Action.WRITE_RED_LIGHT, currentLightSetpoint.getRedValue());
        }
    }
}
