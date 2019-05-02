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
    private int TEMPERATURE_INTERVAL = 10;
    private int intervalTime;

    private double lastHumiditySetpoint;
    private double lastHumidityValue;
    private double lastTemperatureSetpoint;
    private double lastTemperatureValue;
    private LightSetPoint currentLightSetpoint;
    
    private double HUMIDITY_TOLERANCE = 0.5;
    private double TEMPERATURE_TOLERANCE = 0.5;

    private double TEMPERATURE_MAX_FAN_THRESHOLD = 2; // value times diff between middle and max + middle

    private double PROPORTIONAL_VALUE = 0.7;
    private double DERIVATIVE_VALUE = 0.1;

    private Feeder feeder;

    public Regulator(GreenhouseController greenhouseController) {
        this.greenhouseController = greenhouseController;

        lastHumidityValue = greenhouseController.readValue(Action.READ_HUMIDITY);
//        lastTemperatureValue = greenhouseController.readValue(Action.READ_TEMPERATURE);

        feeder = new Feeder();
        lastTemperatureValue = feeder.getValue();

        Thread feederThread = new Thread(feeder);
        feederThread.start();

        addTemperatureSetPoint(25, 35, 10, 90);
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
        lastHumiditySetpoint = (maxValue - minValue) / 2 + minValue;
    }

    public void addTemperatureSetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        temperatureSetpoint = new TemperatureSetPoint(minValue, maxValue, alarmMinValue, alarmMaxValue);
        lastTemperatureSetpoint = (maxValue - minValue) / 2 + minValue;
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

        if (intervalTime % HUMIDITY_INTERVAL  == 0 && humiditySetpoint != null)
            regulateHumidity();

        if (intervalTime % TEMPERATURE_INTERVAL == 0 && temperatureSetpoint!= null)
            regulateTemperature();

        if (!lightSetPointList.isEmpty())
            regulateLight();

//        lastHumidityValue = greenhouseController.readValue(Action.READ_HUMIDITY);
//        lastTemperatureValue = greenhouseController.readValue(Action.READ_TEMPERATURE);

        lastTemperatureValue = feeder.getValue();

        intervalTime++;
    }

    private void regulateHumidity() {
        double humidity = greenhouseController.readValue(Action.READ_HUMIDITY);
//        double humidity = feeder.getValue();
        double middle = (humiditySetpoint.maxValue - humiditySetpoint.minValue) / 2 + humiditySetpoint.minValue;

//        double diff = humidity - lastHumidityValue;
        double offset = Math.abs(lastHumiditySetpoint - humidity);

        if (offset > HUMIDITY_TOLERANCE || lastHumiditySetpoint < humiditySetpoint.minValue
                || lastHumiditySetpoint > humiditySetpoint.maxValue) {
            double newHumiditySetpoint = middle;

            double proportional = Math.abs(middle - humidity) * PROPORTIONAL_VALUE;
            if (humidity > middle) {                    // Above
                newHumiditySetpoint -= proportional;
            } else {                                    // Below
                newHumiditySetpoint += proportional;
            }

//            double derivative = diff * DERIVATIVE_VALUE;
//            System.out.println("Deriviative: " + derivative);
//            if (diff > 0) {                              // Going up
//                newHumiditySetpoint -= derivative;
//            } else if (diff < 0) {                      // Going down
//                newHumiditySetpoint += derivative;
//            }
//            newHumiditySetpoint -= derivative;

            setHumidity(newHumiditySetpoint);
        }

        lastHumidityValue = humidity;
    }

    private void setHumidity(double value) {
        greenhouseController.writeValue(Action.WRITE_MOISTURE, value);
//        feeder.setSetpoint(value);
        lastHumiditySetpoint = value;
    }

    private void regulateTemperature() {
//        double temperature = greenhouseController.readValue(Action.READ_TEMPERATURE);
        double temperature = feeder.getValue();

        double middle = (temperatureSetpoint.maxValue - temperatureSetpoint.minValue) / 2 + temperatureSetpoint.minValue;

        double offset = Math.abs(lastTemperatureSetpoint - temperature);

        if (offset > TEMPERATURE_TOLERANCE || lastTemperatureSetpoint < temperatureSetpoint.minValue
                || lastTemperatureSetpoint > temperatureSetpoint.maxValue) {
            double newTemperatureSetpoint = middle;

            double proportional = Math.abs(middle - temperature) * PROPORTIONAL_VALUE;
            if (temperature > middle) {                    // Above
                newTemperatureSetpoint -= proportional;

                if (temperature > (temperatureSetpoint.maxValue - middle) * TEMPERATURE_MAX_FAN_THRESHOLD + middle)
                    greenhouseController.writeValue(Action.WRITE_FAN_SPEED, 2);
                else
                    greenhouseController.writeValue(Action.WRITE_FAN_SPEED, 1);
            } else {                                    // Below
                newTemperatureSetpoint += proportional;
                greenhouseController.writeValue(Action.WRITE_FAN_SPEED, 0);
            }

            setTemperature(newTemperatureSetpoint);
        }

        lastTemperatureValue = temperature;
    }

    private void setTemperature(double value) {
        greenhouseController.writeValue(Action.WRITE_TEMPERATURE, value);
        feeder.setSetpoint(value);
        lastTemperatureSetpoint = value;
    }

    private void regulateLight() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        String currentTime = dateFormat.format(new Date());

        List<LightSetPoint> setPointsBeforeTime = new ArrayList<>();

        for (LightSetPoint lightSetPoint : lightSetPointList)
            if (currentTime.compareTo(lightSetPoint.getTime()) >= 0)
                setPointsBeforeTime.add(lightSetPoint);

        setPointsBeforeTime.sort(Comparator.comparing(LightSetPoint::getTime));

        if (currentLightSetpoint != setPointsBeforeTime.get(setPointsBeforeTime.size() - 1))
        {
            currentLightSetpoint = setPointsBeforeTime.get(setPointsBeforeTime.size() - 1);
            greenhouseController.writeValue(Action.WRITE_BLUE_LIGHT, currentLightSetpoint.getBlueValue());
            greenhouseController.writeValue(Action.WRITE_RED_LIGHT, currentLightSetpoint.getRedValue());
        }
    }
}

class Feeder implements Runnable{

    private double value;
    private double setpoint;
    private double SLOPE = 0.3;

    public Feeder () {
        value = 0;
        setpoint = 0;
    }

    public void setSetpoint (double setpoint) {
        this.setpoint = setpoint;
        System.out.println("New feeder setpoint: " + setpoint);
    }

    public double getValue () {
        System.out.println("Feeder value: " + value);
        return this.value;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            double diff = setpoint - value;

            value += diff * SLOPE;
        }
    }
}