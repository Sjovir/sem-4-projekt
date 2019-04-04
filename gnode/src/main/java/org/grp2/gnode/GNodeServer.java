package org.grp2.gnode;
import org.grp2.gnode.api.*;


public class GNodeServer {

    public static void main(String[] args) {

        int PORT = 7000;

        API api = new API(PORT);
        api.start();
    }
}
