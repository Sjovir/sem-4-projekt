package org.grp2.gnode.api;
import io.javalin.Context;



public class APIHandler {
    private GNode gnode;

    public APIHandler(GNode gnode) {
        this.gnode = gnode;
    }

    public void readValue(Context context) {
        int type = Integer.parseInt(context.pathParam("type"));
        int value = gnode.readValue(type);

        context.json(value);

    }

    public void writeValue(Context context){
        int type = Integer.parseInt(context.pathParam("type"));
        double value = Integer.parseInt(context.pathParam("value"));
        gnode.writeValue(type, value);
    }

}
