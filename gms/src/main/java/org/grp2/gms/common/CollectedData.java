package org.grp2.gms.common;

public class CollectedData {
    private int id;
    private String timeStamp;
    private double humidity;
    private double temperature;
    private int redLight;
    private int blueLight;

    public CollectedData(int id, String timeStamp, double temperature, double humidity, int redLight, int blueLight) {
        this.id = id;
        this.timeStamp = timeStamp;
        this.temperature = temperature;
        this.humidity = humidity;
        this.redLight = redLight;
        this.blueLight = blueLight;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public int getRedLight() {
        return redLight;
    }

    public void setRedLight(int redLight) {
        this.redLight = redLight;
    }

    public int getBlueLight() {
        return blueLight;
    }

    public void setBlueLight(int blueLight) {
        this.blueLight = blueLight;
    }
}
