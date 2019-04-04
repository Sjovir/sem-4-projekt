package org.grp2.gnode.domain;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.apache.http.HttpRequest;
import org.junit.Test;


class ValueSenderTest {
    private ValueSender valueSender;

    @org.junit.jupiter.api.Test
    void testDeadlocks() {
        valueSender = new ValueSender(1, new GreenhouseControllerStub(9000,"127.0.0.1"));
        Thread t = new Thread(valueSender);

        valueSender.setGMSConnection(9584,"127.0.0.1",1);

        t.start();
        long timestart =System.currentTimeMillis();
        try {
            while((System.currentTimeMillis()-timestart)<10000){
                valueSender.setGMSConnection(9564,"127.0.0.1",2);
                valueSender.setInterval(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @org.junit.jupiter.api.Test
    void testUnirest() throws InterruptedException, UnirestException {
        valueSender = new ValueSender(1000, new GreenhouseControllerStub(7000,"localhost"));
        valueSender.setGMSConnection(7001,"localhost",2);
        Thread t = new Thread(valueSender);
        t.start();
    }
}