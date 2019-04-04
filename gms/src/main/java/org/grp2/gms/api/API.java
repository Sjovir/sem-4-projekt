package org.grp2.gms.api;

import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import org.grp2.gms.domain.GMS;

public class API extends AbstractAPI {

    public API(int PORT) {
        super(PORT);
    }

    @Override
    public void start() {
        Javalin app = Javalin.create()
                .enableRouteOverview("/routes")
                .enableCorsForAllOrigins()
                .enableAutogeneratedEtags()
                .port(PORT);
        APIHandler handler = new APIHandler(new GMS());
        setRoutes(app, handler);
        app.start();
    }

    private void setRoutes(Javalin app, APIHandler handler) {
        app.routes(() -> {
            path("/api", () -> {
                //raspberry pi calls
                post("/write-value/:type/:value", handler::writeValue);
                post("/write-gms-connection/:port/:url/:green-house-id", handler::writeGMSConnection);
                post("/write-humidity-setpoint/:min-value/:max-value/:alarm-min-value/:alarm-max-value", handler::writeHumiditySetPoint);
                post("/write-temperature-setpoint/:min-value/:max-value/:alarm-min-value/:alarm-max-value", handler::writeTemperatureSetPoint);
                post("/write-light-setpoint/:blue-value/:red-value/:time", handler::writeLightSetPoint);
                post("/start-regulator/", handler::startRegulator);

                //gms calls
                post("/write-collected-data/:greenhouse-id/:timestamp/:temperature/:humidity/:red-light/:blue-light", handler::writeCollectedData);
                post("setup-greenhouse/:greenhouse-id/:ip-address/:name/:location", handler::setupGreenhouse);
                get("/get-greenhouse-data/:greenhouse-id", handler::getGreenhouseData);
            });
        });

    }
}