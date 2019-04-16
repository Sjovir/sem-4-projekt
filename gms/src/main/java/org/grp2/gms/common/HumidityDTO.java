package org.grp2.gms.common;

public class HumidityDTO {
    private long timeCollected;
    private double value;

    public HumidityDTO(long timeCollected,double value){
        this.timeCollected=timeCollected;
        this.value=value;
    }

    public long getTimeCollected() {
        return timeCollected;
    }

    public void setTimeCollected(long timeCollected) {
        this.timeCollected = timeCollected;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
