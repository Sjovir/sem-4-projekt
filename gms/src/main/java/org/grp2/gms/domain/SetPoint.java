package org.grp2.gms.domain;

public class SetPoint {
    private String measurementType;
    private String setPointType;
    private int value;

    public SetPoint(String measurementType, String setPointType, int value) {
        this.measurementType = measurementType;
        this.setPointType = setPointType;
        this.value = value;
    }

    public String getMeasurementType() {
        return measurementType;
    }

    public void setMeasurementType(String measurementType) {
        this.measurementType = measurementType;
    }

    public String getSetPointType() {
        return setPointType;
    }

    public void setSetPointType(String setPointType) {
        this.setPointType = setPointType;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
