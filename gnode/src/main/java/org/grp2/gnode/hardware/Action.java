package org.grp2.gnode.hardware;

public enum Action {
    READ_TEMPERATURE (1), READ_HUMIDITY (2), READ_BLUE_LIGHT (3), READ_RED_LIGHT (4), WRITE_TEMPERATURE (5),
    WRITE_WATER_LEVEL (6), WRITE_FAN_SPEED (7), WRITE_BLUE_LIGHT (8), WRITE_RED_LIGHT (9), WRITE_MOISTURE (10);

    private int value;

    Action (int value) {
        this.value = value;
    }

//    public Action getActionFromId (int id) {
//        Action[] array = Action.class.getEnumConstants();
//        int actionId = id - 1;
//        if(0 <= actionId && actionId < array.length)
//            return array[actionId];
//        else
//            return null;
//    }

    public static Action getActionFromID (int id) {

        for (Action currentAction : Action.values())
            if (currentAction.value == id)
                return currentAction;

        return null;
    }
}
