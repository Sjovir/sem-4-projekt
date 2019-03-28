package org.grp2.gnode.api;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import org.grp2.gnode.api.AbstractAPI;
import org.grp2.gnode.domain.GNode;

public class API extends AbstractAPI{

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
        APIHandler handler = new APIHandler(new GNode());
        setRoutes(app, handler);
        app.start();
    }

    private void setRoutes(Javalin app, APIHandler handler) {
        app.routes(() -> {
            path("/api", () -> {
                get("/read-value/:type", handler::readValue);
                post("/write-value/:type/:value", handler::writeValue);
                post("/write-gms-collection/:url/:port/:green-house-id", handler::writeGMSCollection);
                post("/write-humidity-setpoint/:min-value/:max-value/:alarm-min-value/:alarm-max-value", handler::writeHumiditySetPoint);
                post("/write-temperature-setpoint/:min-value/:max-value/:alarm-min-value/:alarm-max-value", handler::writeTemperatureSetPoint);
                post("/write-light-setpoint/:blue-value/:red-value/:time", handler::writeLightSetPoint);
                post("/start-regulator/", handler::startRegulator);
            });
        });
    }
}