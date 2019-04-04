package org.grp2.gms;

import org.grp2.gms.api.API;

public class GMSServer {
    public static void main(String[] args) {

        int PORT = 7001;

        API api = new API(PORT);
        api.start();
    }
}
