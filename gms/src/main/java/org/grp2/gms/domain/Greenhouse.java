package org.grp2.gms.domain;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.*;

public class Greenhouse {
    private int id;
    private String ipAddress;
    private int port;
    private String name;
    private String location;
    private HumiditySetPoint humiditySetPoint;
    private TemperatureSetPoint temperatureSetPoint;
    private LightSetPoint lightSetPoint;


    public Greenhouse(int id, String ipAddress, int port, String name, String location) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.name = name;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHumiditySetPoint(HumiditySetPoint humiditySetPoint){
        this.humiditySetPoint = humiditySetPoint;
        String routeUrl = "write-humidity-setpoint/" + humiditySetPoint.getMinValue() + "/" + humiditySetPoint.getMaxValue() + "/"
                + humiditySetPoint.getAlarmMinValue() + "/" + humiditySetPoint.getAlarmMaxValue();
        writeToGnode(routeUrl);
    }

    public void setTemperatureSetPoint(TemperatureSetPoint temperatureSetPoint){
        this.temperatureSetPoint = temperatureSetPoint;
        String routeUrl = "write-temperature-setpoint/" + temperatureSetPoint.getMinValue() + "/" + temperatureSetPoint.getMaxValue() + "/"
                + temperatureSetPoint.getAlarmMinValue() + "/" + temperatureSetPoint.getAlarmMaxValue();
        writeToGnode(routeUrl);
    }

    public void addLightSetPoint(LightSetPoint lightSetPoint){
        this.lightSetPoint = lightSetPoint;
        String routeUrl = "write-light-setpoint/" + lightSetPoint.getBlueValue() + "/" + lightSetPoint.getRedValue() + "/"
                + lightSetPoint.getTime();
        boolean temp = writeToGnode(routeUrl);

    }

    public boolean writeToGnode(String routeUrl) {
        String ipAddress = this.getIpAddress();
        int port = this.getPort();
        String url = "http://" + ipAddress + ":" + port + "/api/" + routeUrl;

        try {
            Unirest.post(url).asString();
            return true;
        } catch (UnirestException ex) {
            ex.getMessage();
            return false;
        }

    }

}
