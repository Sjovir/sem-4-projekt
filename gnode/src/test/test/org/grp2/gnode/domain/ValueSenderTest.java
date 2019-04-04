package org.grp2.gnode.domain;

import org.grp2.gnode.hardware.GreenhouseController;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ValueSenderTest {
    private ValueSender valueSender;

    @BeforeEach
    void setup(){
        valueSender = new ValueSender(1, new GreenhouseControllerStub(9000,"127.0.0.1"));
    }

    @org.junit.jupiter.api.Test
    void testDeadlocks() {
        Thread t = new Thread(valueSender);

        valueSender.setGMSConnection(9584,"127.0.0.1",1);

        t.start();
        long timestart =System.currentTimeMillis();
        try {
            while((System.currentTimeMillis()-timestart)<1000000000){
                valueSender.setGMSConnection(9564,"127.0.0.1",2);
                valueSender.setInterval(1);

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}