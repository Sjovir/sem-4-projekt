package org.grp2.gms.domain;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.grp2.gms.common.HumiditySetpointDTO;
import org.grp2.gms.common.LightSetpointDTO;
import org.grp2.gms.common.TemperatureSetpointDTO;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Greenhouse {
    private int id;
    private String ipAddress;
    private int port;
    private String location;
    private String name;
    private long dateCreated;
    private HumiditySetPoint humiditySetPoint;
    private TemperatureSetPoint temperatureSetPoint;
    private List<LightSetPoint> lightSetPoints;


    public Greenhouse(int id, String ipAddress, int port, String location, String name, long dateCreated) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.port = port;
        this.name = name;
        this.location = location;
        this.dateCreated = dateCreated;
        this.lightSetPoints = new ArrayList<>();
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

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public HumiditySetPoint getHumiditySetPoint() {
        return humiditySetPoint;
    }

    public TemperatureSetPoint getTemperatureSetPoint() {
        return temperatureSetPoint;
    }

    public List<LightSetPoint> getLightSetPoints() {
        return lightSetPoints;
    }

    public boolean writeValue(int type, double value) {
        String routeUrl = "write-value/" + type + "/" + value;
        return writeToGnode(routeUrl);
    }

    public boolean setHumiditySetPoint(HumiditySetpointDTO humiditySetPointDTO){
        this.humiditySetPoint = convertHumiditySetpoint(humiditySetPointDTO);
        String routeUrl = "write-humidity-setpoint/" + humiditySetPoint.getMinValue() + "/" + humiditySetPoint.getMaxValue() + "/"
                + humiditySetPoint.getAlarmMinValue() + "/" + humiditySetPoint.getAlarmMaxValue();
        return writeToGnode(routeUrl);
    }

    public boolean setTemperatureSetPoint(TemperatureSetpointDTO temperatureSetPointDTO){
        this.temperatureSetPoint = convertTemperatureSetpoint(temperatureSetPointDTO);
        String routeUrl = "write-temperature-setpoint/" + temperatureSetPoint.getMinValue() + "/" + temperatureSetPoint.getMaxValue() + "/"
                + temperatureSetPoint.getAlarmMinValue() + "/" + temperatureSetPoint.getAlarmMaxValue();
        return writeToGnode(routeUrl);
    }

    public boolean addLightSetPoint(LightSetpointDTO lightSetPointDTO){
        lightSetPoints.add(convertLightSetpoint(lightSetPointDTO));
        String routeUrl = "write-light-setpoint/" + lightSetPointDTO.getBlue() + "/" + lightSetPointDTO.getRed() + "/"
                + lightSetPointDTO.getStartTime();
        return writeToGnode(routeUrl);

    }

    public boolean setCallbackConnection(int port, String ipAddress){
        String routeUrl = "write-gms-connection/"+port+"/"+ipAddress+"/"+this.id;
        return writeToGnode(routeUrl);
    }

    public boolean startRegulator(){
        return writeToGnode("start-regulator");
    }

    private boolean writeToGnode(String routeUrl) {
        String ipAddress = this.getIpAddress();
        int port = this.getPort();
        String url = "http://" + ipAddress + ":" + port + "/api/" + routeUrl;

        try {
            InetAddress inet = InetAddress.getByName(ipAddress);
            if(inet.isReachable(port)){
                HttpResponse res = Unirest.post(url).asString();

                if(res.getStatus()==200){
                    return true;
                }
            }
            return false;
        } catch (UnirestException ex) {
            ex.getMessage();
            return false;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private HumiditySetPoint convertHumiditySetpoint(HumiditySetpointDTO humiditySetpointDTO) {
        double min = humiditySetpointDTO.getMin();
        double max = humiditySetpointDTO.getMax();
        double alarmMin = humiditySetpointDTO.getAlarmMin();
        double alarmMax = humiditySetpointDTO.getAlarmMax();

        HumiditySetPoint humiditySetPoint = new HumiditySetPoint(min, max, alarmMin, alarmMax);

        return humiditySetPoint;
    }

    private TemperatureSetPoint convertTemperatureSetpoint(TemperatureSetpointDTO temperatureSetpointDTO) {
        double min = temperatureSetpointDTO.getMin();
        double max = temperatureSetpointDTO.getMax();
        double alarmMin = temperatureSetpointDTO.getAlarmMin();
        double alarmMax = temperatureSetpointDTO.getAlarmMax();

        TemperatureSetPoint temperatureSetpoint = new TemperatureSetPoint(min, max, alarmMin, alarmMax);

        return temperatureSetpoint;
    }

    private LightSetPoint convertLightSetpoint(LightSetpointDTO lightSetpointDTO) {
        int blue = lightSetpointDTO.getBlue();
        int red = lightSetpointDTO.getRed();
        String time = lightSetpointDTO.getStartTime();

        LightSetPoint lightSetPoint = new LightSetPoint(blue, red, time);

        return lightSetPoint;
    }
}
