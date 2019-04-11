package org.grp2.gms.common;

public class LightDTO {

    private long timeCollected;
    private int redValue;
    private int blueValue;

    public LightDTO(long timeCollected, int redValue, int blueValue){
        this.timeCollected=timeCollected;
        this.redValue=redValue;
        this.blueValue=blueValue;
    }

    public long getTimeCollected() {
        return timeCollected;
    }

    public void setTimeCollected(long timeCollected) {
        this.timeCollected = timeCollected;
    }

    public int getRedValue() {
        return redValue;
    }

    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public int getBlueValue() {
        return blueValue;
    }

    public void setBlueValue(int blueValue) {
        this.blueValue = blueValue;
    }
}
