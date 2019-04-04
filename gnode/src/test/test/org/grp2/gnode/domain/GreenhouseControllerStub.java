package org.grp2.gnode.domain;

import org.grp2.gnode.hardware.Action;
import org.grp2.gnode.hardware.GreenhouseController;

public class GreenhouseControllerStub extends GreenhouseController {
    public GreenhouseControllerStub(int port, String ipAddress) {
        super(port, ipAddress);
    }

    @Override
    public Double readValue(Action action) {
        return 5.0;
    }
}
