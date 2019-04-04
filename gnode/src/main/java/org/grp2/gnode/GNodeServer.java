package org.grp2.gnode;
import org.grp2.gnode.api.*;
import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;


public class GNodeServer {

    public static void main(String[] args) {

        int PORT = 7000;

        API api = new API(PORT);
        api.start();
    }
}
