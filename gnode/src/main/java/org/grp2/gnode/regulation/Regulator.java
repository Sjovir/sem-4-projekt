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

    private int HUMIDITY_INTERVAL = 10;
    private int TEMPERATURE_INTERVAL = 5;
    private int intervalTime;

    private double lastHumiditySetpoint;
    private double lastHumidityValue;
    private LightSetPoint currentLightSetpoint;
    
    private double HUMIDITY_TOLERANCE = 0.5;
    private double TEMPERATURE_TOLERANCE = 0.5;

    private double PROPORTIONAL_VALUE = 0.7;
    private double DERIVATIVE_VALUE = 0.1;

    public Regulator(GreenhouseController greenhouseController) {
        this.greenhouseController = greenhouseController;
        
        lastHumidityValue = greenhouseController.readValue(Action.READ_HUMIDITY);
        
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

        if (intervalTime % HUMIDITY_INTERVAL  == 0)
            regulateHumidity();

        if (intervalTime % TEMPERATURE_INTERVAL == 0)
            regulateTemperature();

        regulateLight();

        intervalTime++;
    }

    private void regulateHumidity() {
        double humidity = greenhouseController.readValue(Action.READ_HUMIDITY);

        double middle = (humiditySetpoint.maxValue - humiditySetpoint.minValue) / 2 + humiditySetpoint.minValue;

        double diff = lastHumidityValue - humidity;
        double offset = Math.abs(lastHumiditySetpoint - humidity);

        if (offset > HUMIDITY_TOLERANCE) {
            double newHumiditySetpoint = middle;

            double proportional = Math.abs(middle - humidity) * PROPORTIONAL_VALUE;
            if (humidity > middle) {                    // Above
                newHumiditySetpoint -= proportional;
            } else {                                    // Below
                newHumiditySetpoint += proportional;
            }
            
            double derivative = diff * DERIVATIVE_VALUE;
            if (diff > 0) {                              // Going up
                newHumiditySetpoint -= derivative;
            } else if (diff < 0) {                      // Going down
                newHumiditySetpoint += derivative;
            }

            setHumidity(newHumiditySetpoint);
        }


        lastHumidityValue = humidity;
    }

    private void setHumidity(double value) {
        greenhouseController.writeValue(Action.WRITE_MOISTURE, value);
        lastHumiditySetpoint = value;
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
