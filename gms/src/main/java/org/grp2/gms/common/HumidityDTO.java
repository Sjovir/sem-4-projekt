package org.grp2.gms.common;

public class HumidityDTO {
    private long time_collected;
    private double value;

    public HumidityDTO(long time_collected,double value){
        this.time_collected=time_collected;
        this.value=value;
    }

    public long getTime_collected() {
        return time_collected;
    }

    public void setTime_collected(long time_collected) {
        this.time_collected = time_collected;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
