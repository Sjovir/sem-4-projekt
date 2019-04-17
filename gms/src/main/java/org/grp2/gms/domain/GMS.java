package org.grp2.gms.domain;

import org.grp2.gms.common.*;
import org.grp2.gms.dao.DAO;

import java.util.ArrayList;
import java.util.List;

public class GMS {
    private DAO gmsDao;
    private ArrayList<Greenhouse> greenhouseList;

    public GMS() {
        gmsDao = new DAO();
        greenhouseList = new ArrayList<>();
        loadGreenhouses();
    }

    public boolean writeCollectedData(int greenhouseID, LightDTO lightData, HumidityDTO humidityData,
                                      TemperatureDTO temperatureData) {
        boolean result = gmsDao.writeCollectedData(greenhouseID, lightData, humidityData, temperatureData);

        return result;
    }

    public boolean setHumiditySetPoint(int id, HumiditySetpointDTO humiditySetPointDTO){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null && gmsDao.writeHumiditySetpoint(id, humiditySetPointDTO)) {
            greenhouse.setHumiditySetPoint(humiditySetPointDTO);

            return true;
        }
        return false;
    }

    public boolean setTemperatureSetPoint(int id, TemperatureSetpointDTO temperatureSetPointDTO){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null && gmsDao.writeTemperatureSetpoint(id, temperatureSetPointDTO)) {
            greenhouse.setTemperatureSetPoint(temperatureSetPointDTO);

            return true;
        }
        return false;
    }

    public boolean addLightSetPoint(int id, LightSetpointDTO lightSetPointDTO){
        Greenhouse greenhouse = getGreenhouse(id);

        if (greenhouse != null && gmsDao.writeLightSetpoint(id, lightSetPointDTO)) {
            greenhouse.addLightSetPoint(lightSetPointDTO);

            return true;
        }
        return false;
    }

    public GreenhouseDTO getGreenhouseData(int id) {
        GreenhouseDTO greenhouseDTO = gmsDao.getGreenhouseData(id);

        return greenhouseDTO;
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

    private Greenhouse getGreenhouse(int id){
        for (Greenhouse greenhouse: greenhouseList) {
            if (greenhouse.getId()==id){
                return greenhouse;
            }

        }
        return null;
    }

    private void loadGreenhouses() {
        for(GreenhouseDTO gh : gmsDao.getGreenhouseObjects()) {
            Greenhouse greenhouse = convertGreenhouseDTO(gh);

            HumiditySetpointDTO humiditySetpointDTO = gmsDao.getHumiditySetpointObject(greenhouse.getId());
            TemperatureSetpointDTO temperatureSetpointDTO = gmsDao.getTemperatureSetpointObject(greenhouse.getId());
            List<LightSetpointDTO> lightSetpointDTOList = gmsDao.getLightSetpointObjects(greenhouse.getId());

            if(humiditySetpointDTO != null) {
                greenhouse.setHumiditySetPoint(humiditySetpointDTO);
            }
            if(temperatureSetpointDTO != null) {
                greenhouse.setTemperatureSetPoint(temperatureSetpointDTO);
            }
            if(lightSetpointDTOList!= null && lightSetpointDTOList.size() > 0) {
                for(LightSetpointDTO lightSetpointDTO : lightSetpointDTOList) {
                    greenhouse.addLightSetPoint(lightSetpointDTO);
                }
            }

            greenhouseList.add(greenhouse);

        }
    }

    private Greenhouse convertGreenhouseDTO(GreenhouseDTO greenhouseDTO) {
        String ip = greenhouseDTO.getIpAddress();
        int port = greenhouseDTO.getPort();
        String location = greenhouseDTO.getLocation();
        String name = greenhouseDTO.getName();
        int id = greenhouseDTO.getId();

        Greenhouse greenhouse = new Greenhouse(id, ip, port, name, location);

        return greenhouse;
    }

}
