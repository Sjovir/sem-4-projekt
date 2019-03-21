package org.grp2.gnode.hardware;

public enum Action {
    READ_TEMPERATURE (1), READ_HUMIDITY (2), WRITE_TEMPERATURE (3), WRITE_WATER_LEVEL (4),
    WRITE_FAN_SPEED (5), WRITE_BLUE_LIGHT (6), WRITE_RED_LIGHT (7), WRITE_MOISTURE (8);

    private int value;

    Action (int value) {
        this.value = value;
    }

    public Action getActionFromId (int id) {
        Action[] array = Action.class.getEnumConstants();
        int actionId = id - 1;
        if(0 <= actionId && actionId < array.length)
            return array[actionId];
        else
            return null;
    }
}
