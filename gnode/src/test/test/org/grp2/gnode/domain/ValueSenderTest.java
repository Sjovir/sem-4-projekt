package org.grp2.gnode.domain;

import org.grp2.gnode.hardware.GreenhouseController;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ValueSenderTest {
    private ValueSender valueSender;

    @BeforeEach
    void setup(){
        valueSender = new ValueSender(1, new GreenhouseControllerStub(9000,"localhost"));
    }

    @org.junit.jupiter.api.Test
    void testDeadlocks() {
        Thread t = new Thread(valueSender);

        valueSender.setGMSConnection(9584,"localhost",1);

        t.start();
        long timestart =System.currentTimeMillis();

        while((System.currentTimeMillis()-timestart)<60000){
            valueSender.setGMSConnection(9564,"localhost",2);
            valueSender.setInterval(1);
        }
    }
}