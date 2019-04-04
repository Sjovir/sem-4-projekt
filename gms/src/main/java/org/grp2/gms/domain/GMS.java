package org.grp2.gms.domain;

import org.grp2.gms.common.CollectedData;
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

    public void getGreenhouseData() {
        //gmsDao.readData();

    }

    public void setupGreenhouse(Greenhouse greenhouse) {
        //gmsDao.setupGreenhouse();
        System.out.println("GreenhouseID: " + greenhouse.getId() + " \n IP: " + greenhouse.getIpAddress() +
                " \nname: " + greenhouse.getName() +  " \nlocation: " + greenhouse.getLocation());
    }

}
