package org.grp2.gnode.hardware;

import org.grp2.gnode.hardware.GreenhouseAPI.GreenhouseAPI.Greenhouse;
import org.grp2.gnode.hardware.GreenhouseAPI.GreenhouseAPI.IGreenhouse;
import org.grp2.gnode.hardware.GreenhouseAPI.PLCCommunication.PLCConnection;
import org.grp2.gnode.hardware.GreenhouseAPI.PLCCommunication.UDPConnection;

public class GreenhouseController {

    private final int port;
    private final String ipAddress;
    private IGreenhouse greenhouse;

    public GreenhouseController (int port, String ipAddress) {
        this.port = port;
        this.ipAddress = ipAddress;

        establishConnection();
    }

    /**
     * Reads a value of the greenhouse.
     * @param action the action to be carried out.
     * @return the double value of the property. Returns null if the property is invalid.
     */
    public Double readValue (Action action) {
        switch (action) {
            case READ_TEMPERATURE:
                return greenhouse.ReadTemp1();
            case READ_HUMIDITY:
                return greenhouse.ReadMoist();
            case READ_BLUE_LIGHT:
                return (double) greenhouse.GetStatus()[5];
            case READ_RED_LIGHT:
                return (double) greenhouse.GetStatus()[4];
            default:
                System.out.println("Not a valid read action");
                return null;
        }
    }

    /**
     * Writes a value to the greenhouse.
     * WRITE_TEMPERATURE: value in degress celcius between 0 and 30.
     * WRITE_WATER_LEVEL: value in seconds between 0 and 120.
     * WRITE_FAN_SPEED: value of 0, 1, and 2.
     * WRITE_BLUE_LIGHT: value between 0 and 100.
     * WRITE_RED_LIGHT: value between 0 and 100.
     * WRITE_MOISTURE: value in % between 10 and 90.
     * @param action the action to be carried out.
     * @param value the value which will be cast to an integer in most cases.
     * @return a boolean describing whether or not the write command is carried out.
     */
    public boolean writeValue (Action action, double value) {
        switch (action) {
            case WRITE_TEMPERATURE:
                return greenhouse.SetTemperature(273 + (int) value);
            case WRITE_WATER_LEVEL:
                return greenhouse.AddWater((int) value);
            case WRITE_FAN_SPEED:
                return greenhouse.SetFanSpeed((int) value);
            case WRITE_BLUE_LIGHT:
                return greenhouse.SetBlueLight((int) value);
            case WRITE_RED_LIGHT:
                return greenhouse.SetRedLight((int) value);
            case WRITE_MOISTURE:
                return greenhouse.SetMoisture((int) value);
            default:
                System.out.println("Not a valid write action");
                return false;
        }
    }

    private void establishConnection () {
        PLCConnection connection = new UDPConnection(port, ipAddress);

        greenhouse = new Greenhouse(connection);
    }
}
