package org.grp2.gms.domain;

import org.grp2.gms.common.*;
import org.grp2.gms.dao.DAO;

import java.util.ArrayList;

public class GMS {
    private DAO gmsDao;
    private ArrayList<Greenhouse> greenhouseList;

    public GMS() {
        gmsDao = new DAO();
        greenhouseList = new ArrayList<>();
    }

    public boolean writeCollectedData(LightDTO light, HumidityDTO humid, TemperatureDTO temp) {
        //gmsDao.writeData();
        //write to gmsdao
        return false;
    }
    private Greenhouse getGreenhouse(int id){
        for (Greenhouse greenhouse: greenhouseList) {
            if (greenhouse.getId()==id){
                return greenhouse;
            }

        }
        return null;
    }

    public boolean setHumiditySetPoint(int id, HumiditySetPoint humiditySetPoint){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null) {
            greenhouse.setHumiditySetPoint(humiditySetPoint);
            return true;
        }
        return false;
    }

    public boolean setTemperatureSetPoint(int id, TemperatureSetPoint temperatureSetPoint){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null) {
            greenhouse.setTemperatureSetPoint(temperatureSetPoint);
            return true;
        }
        return false;
    }

    public boolean addLightSetPoint(int id, LightSetPoint lightSetPoint){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null) {
            greenhouse.addLightSetPoint(lightSetPoint);
            return true;
        }
        return false;
    }

    public GreenhouseDTO getGreenhouseData(int id) {
        GreenhouseDTO dto = new GreenhouseDTO("i badet",34823423,"localhost",4,"mitnavn",
                new LightDTO(342234,45,55),
                new HumidityDTO(3458345,23.2),
                new TemperatureDTO(2342342,25.2));
        return dto;
    }

    public boolean setupGreenhouse(Greenhouse greenhouse) {
        //gmsDao.setupGreenhouse();
        greenhouseList.add(greenhouse);

        if (greenhouseList.contains(greenhouse)) {
            System.out.println("GreenhouseID: " + greenhouse.getId() + " \n IP: " + greenhouse.getIpAddress() +
                    " \nname: " + greenhouse.getName() + " \nlocation: " + greenhouse.getLocation());
            return true;
        }
        return false;
    }

    public boolean setGMSConnectionOnGreenhouse(int greenhouseid, int port, String ipAddress){
        Greenhouse greenhouse=getGreenhouse(greenhouseid);
        if(greenhouse==null){
            System.err.println("Greenhouse "+greenhouseid+" does not exist");
            return false;
        }
        return greenhouse.setCallbackConnection(port,ipAddress);
    }

    public boolean startRegulator(int id){
        return getGreenhouse(id).startRegulator();
    }

}
