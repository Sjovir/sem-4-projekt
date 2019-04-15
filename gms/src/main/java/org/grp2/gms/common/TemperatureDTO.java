package org.grp2.gms.common;

public class TemperatureDTO {
    private long timeCollected;
    private double value;
    public TemperatureDTO(long timeCollected,double value){
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
