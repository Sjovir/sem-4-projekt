package org.grp2.gms.domain;

import org.grp2.gms.common.*;
import org.grp2.gms.dao.GMSDAO;

import java.util.ArrayList;

public class GMS {
    private GMSDAO gmsDao;
    private ArrayList<Greenhouse> greenhouseList;

    public GMS() {
        gmsDao = new GMSDAO();
    }

    public void writeCollectedData(CollectedData collectedData) {
        //gmsDao.writeData();
        System.out.println(collectedData.getId() + " " + collectedData.getTimeStamp() + " " +
                collectedData.getTemperature() + " " + collectedData.getHumidity() + " " +
                collectedData.getRedLight() + " " + collectedData.getBlueLight());
    }

    public GreenhouseDTO getGreenhouseData(int id) {
        GreenhouseDTO dto = new GreenhouseDTO("i badet",34823423,"localhost",4,"mitnavn",
                new LightDTO(342234,45,55),
                new HumidityDTO(3458345,23.2),
                new TemperatureDTO(2342342,25.2));
        return dto;
    }

    public void setupGreenhouse(Greenhouse greenhouse) {
        //gmsDao.setupGreenhouse();
        System.out.println("GreenhouseID: " + greenhouse.getId() + " \n IP: " + greenhouse.getIpAddress() +
                " \nname: " + greenhouse.getName() +  " \nlocation: " + greenhouse.getLocation());
    }

}
