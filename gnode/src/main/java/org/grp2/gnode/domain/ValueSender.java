package org.grp2.gnode.domain;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;

import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValueSender implements Runnable {
    private int gmsPort;
    private String gmsURL;
    private long interval;
    private GreenhouseController greenhouseController;
    private int nodeID;

    /**
     * Creates a ValueSender with an initial interval to
     * @param interval in miliseconds
     */
    public ValueSender(long interval, GreenhouseController greenhouseController){
        this.interval=interval;
        this.greenhouseController=greenhouseController;
    }

    public synchronized void setGMSConnection(int gmsPort,String gmsURL,int nodeID){
        this.gmsURL=gmsURL;
        this.nodeID=nodeID;
        this.gmsPort=gmsPort;
    }

    /**
     * set a new interval for sending values to the GMS
     * @param interval
     */
    public synchronized void setInterval(int interval){
        this.interval=interval;
    }

    /**
     * not finished implementing.
     */
    private void sendValues(){
        String tempgmsURL;
        int tempgmsPort;
        int tempnodeID;
        Double temperature;
        Double humidity;
        int bluelight;
        int redlight;
        synchronized (this){
            tempgmsURL=this.gmsURL;
            tempgmsPort=this.gmsPort;
            tempnodeID=this.nodeID;
        }

        synchronized (greenhouseController) {
            temperature = greenhouseController.readValue(Action.READ_TEMPERATURE);
            humidity = greenhouseController.readValue(Action.READ_HUMIDITY);
            redlight =greenhouseController.readValue(Action.READ_RED_LIGHT).intValue();
            bluelight=greenhouseController.readValue(Action.READ_BLUE_LIGHT).intValue();
        }

        StringBuilder request = new StringBuilder();
        request.append("http://")
                .append(tempgmsURL)
                .append(":")
                .append(tempgmsPort)
                .append("/api/write-collected-data/")
                .append(tempnodeID+"/")
                .append(System.currentTimeMillis()+"/")
                .append(temperature.toString()+"/")
                .append(humidity.toString()+"/")
                .append(redlight+"/")
                .append(bluelight);

        try {
            Unirest.post(request.toString()).asString();

        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendValues();
        }

    }
}
