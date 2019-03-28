package org.grp2.gnode.api;

public abstract class AbstractAPI {
    protected final String URL;
    protected final int PORT;

    public AbstractAPI(String URL, int PORT) {
        this.URL = URL;
        this.PORT = PORT;

    }

    public abstract void start();

}
