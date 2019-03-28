package org.grp2.gnode;
import org.grp2.gnode.api.*;


public class GNodeServer {

    public static void main(String[] args) {

        String URL = "";
        int PORT = 7000;

        API api = new API(URL, PORT);
        api.start();
    }
}
