package org.grp2.gnode.regulation;

public class HumiditySetPoint {
    double minValue;
    double maxValue;
    double alarmMinValue;
    double alarmMaxValue;

    public HumiditySetPoint(double minValue, double maxValue, double alarmMinValue, double alarmMaxValue) {
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.alarmMinValue = alarmMinValue;
        this.alarmMaxValue = alarmMaxValue;
    }

    public double getMinValue() {
        return minValue;
    }

    public void setMinValue(double minValue) {
        this.minValue = minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(double maxValue) {
        this.maxValue = maxValue;
    }

    public double getAlarmMinValue() {
        return alarmMinValue;
    }

    public void setAlarmMinValue(double alarmMinValue) {
        this.alarmMinValue = alarmMinValue;
    }

    public double getAlarmMaxValue() {
        return alarmMaxValue;
    }

    public void setAlarmMaxValue(double alarmMaxValue) {
        this.alarmMaxValue = alarmMaxValue;
    }
}
