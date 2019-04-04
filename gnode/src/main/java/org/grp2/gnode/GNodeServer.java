package org.grp2.gnode;
import org.grp2.gnode.api.*;
import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;


public class GNodeServer {

    public static void main(String[] args) {

        int PORT = 7000;

//        API api = new API(PORT);
//        api.start();

        GreenhouseController greenhouseController = new GreenhouseController(5000, "192.168.0.40");
        greenhouseController.writeValue(Action.WRITE_FAN_SPEED, 1);
        greenhouseController.writeValue(Action.WRITE_BLUE_LIGHT, 100);
        System.out.println(greenhouseController.readValue(Action.READ_HUMIDITY));
        System.out.println(greenhouseController.readValue(Action.READ_TEMPERATURE));
        System.out.println(greenhouseController.readValue(Action.READ_BLUE_LIGHT));
        System.out.println(greenhouseController.readValue(Action.READ_RED_LIGHT));
    }
}
