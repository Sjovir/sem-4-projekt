package org.grp2.gnode.api;

public abstract class AbstractAPI {
    protected final int PORT;

    public AbstractAPI(int PORT) {
        this.PORT = PORT;

    }

    public abstract void start();

}
