package org.grp2.gms.common;

public class LightSetpointDTO {
    private long dateCreated;
    private int greenhouseID;
    private int red;
    private int blue;
    private String startTime;

    public LightSetpointDTO(long dateCreated, int greenhouseID, int red, int blue, String startTime) {
        this.dateCreated = dateCreated;
        this.greenhouseID = greenhouseID;
        this.red = red;
        this.blue = blue;
        this.startTime = startTime;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Long dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getGreenhouseID() {
        return greenhouseID;
    }

    public void setGreenhouseID(int greenhouseID) {
        this.greenhouseID = greenhouseID;
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
