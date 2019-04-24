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

    public boolean writeValue(int greenhouseID, int type, double value) {
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        boolean result = greenhouse.writeValue(type, value);

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

    public boolean setTemperatureSetPoint(int greenhouseID, TemperatureSetpointDTO temperatureSetPointDTO){
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        if (greenhouse != null && gmsDao.writeTemperatureSetpoint(greenhouseID, temperatureSetPointDTO)) {
            greenhouse.setTemperatureSetPoint(temperatureSetPointDTO);

            return true;
        }
        return false;
    }

    public boolean addLightSetPoint(int greenhouseID, LightSetpointDTO lightSetPointDTO){
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        if (greenhouse != null && gmsDao.writeLightSetpoint(greenhouseID, lightSetPointDTO)) {
            greenhouse.addLightSetPoint(lightSetPointDTO);

            return true;
        }
        return false;
    }

    public GreenhouseDTO getGreenhouseData(int greenhouseID) {
        GreenhouseDTO greenhouseDTO = gmsDao.getGreenhouseData(greenhouseID);

        return greenhouseDTO;
    }

    public boolean setupGreenhouse(GreenhouseDTO greenhouseDTO) {
        int greenhouseID = gmsDao.writeGreenhouse(greenhouseDTO);
        if(greenhouseID >= 0) {

            greenhouseDTO.setId(greenhouseID);
            Greenhouse greenhouse = convertGreenhouseDTO(greenhouseDTO);
            greenhouseList.add(greenhouse);
            return true;
        }
        return false;
    }

    public boolean setGMSConnectionOnGreenhouse(int greenhouseID, int port, String ipAddress) {
        Greenhouse greenhouse = getGreenhouse(greenhouseID);
        if(greenhouse == null){
            System.err.println("Greenhouse " + greenhouseID + " does not exist");
            return false;
        }
        return greenhouse.setCallbackConnection(port,ipAddress);
    }

    public boolean startRegulator(int greenhouseID) {
        return getGreenhouse(greenhouseID).startRegulator();
    }

    private Greenhouse getGreenhouse(int greenhouseID) {
        for (Greenhouse greenhouse: greenhouseList) {
            if (greenhouse.getId() == greenhouseID){
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

    public HumiditySetpointDTO getHumiditySetpoint(int greenhouseID) {
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        if(greenhouse != null) {
            HumiditySetPoint humiditySetpoint = greenhouse.getHumiditySetPoint();

            return convertHumditySetpoint(humiditySetpoint);
        }
        return null;
    }

    public TemperatureSetpointDTO getTemperatureSetpoint(int greenhouseID) {
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        if(greenhouse != null) {
            TemperatureSetPoint temperatureSetpointDTO = greenhouse.getTemperatureSetPoint();
            return convertTemperatureSetpoint(temperatureSetpointDTO);
        }
        return null;
    }

    public List<LightSetpointDTO> getLightSetpoints(int greenhouseID) {
        Greenhouse greenhouse = getGreenhouse(greenhouseID);

        if(greenhouse != null) {
            List<LightSetpointDTO> lightSetPoints = new ArrayList<>();

            for(LightSetPoint lightSetPoint : greenhouse.getLightSetPoints()) {
                lightSetPoints.add(convertLightsetpoints(lightSetPoint));
            }
            return lightSetPoints;
        }
        return null;
    }

    public List<GreenhouseDTO> getGreenhouses() {
        List<GreenhouseDTO> greenhouseDTOs = new ArrayList<>();
        
        for (Greenhouse greenhouse : greenhouseList) {
            GreenhouseDTO greenhouseDTO = convertGreenhouse(greenhouse);
            greenhouseDTOs.add(greenhouseDTO);
        }

        return greenhouseDTOs;
    }

    private Greenhouse convertGreenhouseDTO(GreenhouseDTO greenhouseDTO) {
        int id = greenhouseDTO.getId();
        String ip = greenhouseDTO.getIpAddress();
        int port = greenhouseDTO.getPort();
        String location = greenhouseDTO.getLocation();
        String name = greenhouseDTO.getName();
        long dateCreated = greenhouseDTO.getDateCreated();

        Greenhouse greenhouse = new Greenhouse(id, ip, port, location, name, dateCreated);

        return greenhouse;
    }

    private GreenhouseDTO convertGreenhouse(Greenhouse greenhouse) {
        String ip = greenhouse.getIpAddress();
        int port = greenhouse.getPort();
        String location = greenhouse.getLocation();
        String name = greenhouse.getName();
        long dateCreated = greenhouse.getDateCreated();
        int id = greenhouse.getId();

        GreenhouseDTO greenhouseDTO = new GreenhouseDTO(ip, port, location, name, dateCreated, id);

        return greenhouseDTO;
    }

    private HumiditySetpointDTO convertHumditySetpoint(HumiditySetPoint humiditySetPoint) {
        double min = humiditySetPoint.getMinValue();
        double max = humiditySetPoint.getMaxValue();
        double alarmMin = humiditySetPoint.getAlarmMinValue();
        double alarmMax = humiditySetPoint.getAlarmMaxValue();

        HumiditySetpointDTO humiditySetpointDTO = new HumiditySetpointDTO(min, max, alarmMin, alarmMax);

        return humiditySetpointDTO;
    }

    private TemperatureSetpointDTO convertTemperatureSetpoint(TemperatureSetPoint temperatureSetPoint) {
        double min = temperatureSetPoint.getMinValue();
        double max = temperatureSetPoint.getMaxValue();
        double alarmMin = temperatureSetPoint.getAlarmMinValue();
        double alarmMax = temperatureSetPoint.getAlarmMaxValue();

        TemperatureSetpointDTO temperatureSetPointDTO = new TemperatureSetpointDTO(min, max, alarmMin, alarmMax);

        return temperatureSetPointDTO;
    }

    private LightSetpointDTO convertLightsetpoints(LightSetPoint lightSetPoint) {
        int redValue = lightSetPoint.getRedValue();
        int blueValue = lightSetPoint.getBlueValue();
        String startTime = lightSetPoint.getTime();

        LightSetpointDTO lightSetpointDTO = new LightSetpointDTO(redValue, blueValue, startTime);

        return lightSetpointDTO;

    }
}
