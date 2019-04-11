package org.grp2.gms.common;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class GreenhouseDTO {
    private String location;
    private long dateCreated;
    private String ipAddress;
    private int id;
    private String name;
    private LightDTO light;
    private HumidityDTO humidity;
    private TemperatureDTO temperature;

    public GreenhouseDTO(String location, long dateCreated, String ipAddress, int id, String name, LightDTO light, HumidityDTO humidity, TemperatureDTO temperature){
        this.location=location;
        this.dateCreated=dateCreated;
        this.ipAddress=ipAddress;
        this.id=id;
        this.name=name;
        this.light=light;
        this.humidity=humidity;
        this.temperature=temperature;
    }




    /**
     * Returns all the values as a HashMap
     * @return
     */
    public Map valuesAsMap(){
        return new HashMap();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LightDTO getLight() {
        return light;
    }

    public void setLight(LightDTO light) {
        this.light = light;
    }

    public HumidityDTO getHumidity() {
        return humidity;
    }

    public void setHumidity(HumidityDTO humidity) {
        this.humidity = humidity;
    }

    public TemperatureDTO getTemperature() {
        return temperature;
    }

    public void setTemperature(TemperatureDTO temperature) {
        this.temperature = temperature;
    }
}
