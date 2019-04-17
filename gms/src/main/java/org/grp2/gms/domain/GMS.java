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
