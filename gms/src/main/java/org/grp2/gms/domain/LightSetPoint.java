package org.grp2.gms.domain;


public class LightSetPoint {

    int id;
    double blueValue;
    double redValue;
    String time;

    public LightSetPoint(int id, double blueValue, double redValue, String time){
        this.id = id;
        this.blueValue = blueValue;
        this.redValue = redValue;
        this.time = time;
    }


    public double getBlueValue() {
        return blueValue;
    }

    public void setBlueValue(double blueValue) {
        this.blueValue = blueValue;
    }

    public double getRedValue() {
        return redValue;
    }

    public void setRedValue(double redValue) {
        this.redValue = redValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
