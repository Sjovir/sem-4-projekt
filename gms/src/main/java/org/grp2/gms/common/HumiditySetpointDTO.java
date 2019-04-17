package org.grp2.gms.common;

public class HumiditySetpointDTO {
    private Long dateCreated;
    private int greenhouseID;
    private double min;
    private double max;
    private double alarmMin;
    private double alarmMax;

    public HumiditySetpointDTO(double min, double max, double alarmMin, double alarmMax) {
        this.min = min;
        this.max = max;
        this.alarmMin = alarmMin;
        this.alarmMax = alarmMax;
    }

    public HumiditySetpointDTO(Long dateCreated, int greenhouseID, double min, double max, double alarmMin, double alarmMax) {
        this.dateCreated = dateCreated;
        this.greenhouseID = greenhouseID;
        this.min = min;
        this.max = max;
        this.alarmMin = alarmMin;
        this.alarmMax = alarmMax;
    }

    public Long getDateCreated() {
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

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAlarmMin() {
        return alarmMin;
    }

    public void setAlarmMin(double alarmMin) {
        this.alarmMin = alarmMin;
    }

    public double getAlarmMax() {
        return alarmMax;
    }

    public void setAlarmMax(double alarmMax) {
        this.alarmMax = alarmMax;
    }
}
