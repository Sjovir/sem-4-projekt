package org.grp2.gms.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GreenhouseDTO {
    private String location;
    private long dateCreated;
    private String ipAddress;
    private int port;
    private int id;
    private String name;
    private List<LightDTO> lightData;
    private List<HumidityDTO> humidityData;
    private List<TemperatureDTO> temperatureData;

    public GreenhouseDTO(String location, long dateCreated, String ipAddress, int port, int id, String name, List<LightDTO> lightData, List<HumidityDTO> humidityData, List<TemperatureDTO> temperatureData){
        this.location = location;
        this.dateCreated = dateCreated;
        this.ipAddress = ipAddress;
        this.port = port;
        this.id = id;
        this.name = name;
        this.lightData = lightData;
        this.humidityData = humidityData;
        this.temperatureData = temperatureData;
    }

    public GreenhouseDTO(String ipAddress, int port, String location, String name, Long dateCreated, int greenhouseID) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.location = location;
        this.name = name;
        this.dateCreated = dateCreated;
        this.id = greenhouseID;
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

    public int getPort() { return port; }

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

    public List<LightDTO> getLight() {
        return lightData;
    }

    public void setLight(List<LightDTO> lightData) {
        this.lightData = lightData;
    }

    public List<HumidityDTO> getHumidity() {
        return humidityData;
    }

    public void setHumidity(List<HumidityDTO> humidityData) {
        this.humidityData = humidityData;
    }

    public List<TemperatureDTO> getTemperature() {
        return temperatureData;
    }

    public void setTemperature(List<TemperatureDTO> temperatureData) {
        this.temperatureData = temperatureData;
    }
}
