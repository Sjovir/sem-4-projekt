package org.grp2.gms.domain;


public class LightSetPoint {

    int blueValue;
    int redValue;
    String time;

    public LightSetPoint(int blueValue, int redValue, String time){
        this.blueValue = blueValue;
        this.redValue = redValue;
        this.time = time;
    }


    public int getBlueValue() {
        return blueValue;
    }

    public void setBlueValue(int blueValue) {
        this.blueValue = blueValue;
    }

    public int getRedValue() {
        return redValue;
    }

    public void setRedValue(int redValue) {
        this.redValue = redValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
