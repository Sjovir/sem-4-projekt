package org.grp2.gms.dao;

import org.grp2.gms.common.GreenhouseDTO;
import org.grp2.gms.common.HumidityDTO;
import org.grp2.gms.common.LightDTO;
import org.grp2.gms.common.TemperatureDTO;
import org.grp2.gms.domain.Greenhouse;
import org.grp2.gms.domain.HumiditySetPoint;
import org.grp2.gms.domain.LightSetPoint;
import org.grp2.gms.domain.TemperatureSetPoint;

import javax.swing.text.html.HTMLDocument;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {

    private Connection connection;

    public DAO() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Dao connected");

//        System.out.println("Light Data: " + getLightData());
    }

    public GreenhouseDTO getGreenhouseData (int greenhouseID) {
        List<LightDTO> lightData = getLightData(greenhouseID);
        List<HumidityDTO> humidityData = getHumidityData(greenhouseID);
        List<TemperatureDTO> temperatureData = getTemperatureData(greenhouseID);

        GreenhouseDTO greenhouseDTO = getGreenhouse(greenhouseID);
        if (greenhouseDTO != null) {
            greenhouseDTO.setLight(lightData);
            greenhouseDTO.setHumidity(humidityData);
            greenhouseDTO.setTemperature(temperatureData);
        }

        return greenhouseDTO;
    }

    public boolean writeCollectedData (int greenhouseID, LightDTO lightData, HumidityDTO humidityData,
                                       TemperatureDTO temperatureData) {
        if (insertLightData(greenhouseID, lightData) && insertHumidityData(greenhouseID, humidityData) &&
                insertTemperatureData(greenhouseID, temperatureData))
            return true;
        else
            return false;
    }

    public boolean writeHumiditySetpoint(int GreenhouseId, HumiditySetPoint humiditySetPoint) {

        if(deleteHumiditySetpoint(GreenhouseId) && insertHumiditySetpoint(GreenhouseId, humiditySetPoint)) {
            return true;
        } else
            return false;
    }

    public boolean writeTemperatureSetpoint(int GreenhouseId, TemperatureSetPoint temperatureSetPoint) {

        if(deleteTemperatureSetpoint(GreenhouseId) && insertTemperatureSetpoint(GreenhouseId, temperatureSetPoint)) {
            return true;
        } else
            return false;
    }

    public boolean writeLightSetpoint(int GreenhouseId, LightSetPoint lightSetPoint) {

        if(deleteLightSetpoint(GreenhouseId) && insertLightSetpoint(GreenhouseId, lightSetPoint)) {
            return true;
        } else
            return false;
    }

    private boolean insertLightData (int greenhouseID, LightDTO lightData) {
        String sql = "INSERT INTO light_data (time_collected, greenhouse_id, red_value, blue_value) " +
                     "VALUES(?, ?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, lightData.getTimeCollected());
            ps.setInt(2, greenhouseID);
            ps.setInt(3, lightData.getRedValue());
            ps.setInt(4, lightData.getBlueValue());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean insertHumidityData (int greenhouseID, HumidityDTO humidityData) {
        String sql = "INSERT INTO humidity_data (time_collected, greenhouse_id, value) " +
                     "VALUES(?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, humidityData.getTimeCollected());
            ps.setInt(2, greenhouseID);
            ps.setDouble(3, humidityData.getValue());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean insertTemperatureData (int greenhouseID, TemperatureDTO temperatureData) {
        String sql = "INSERT INTO temperature_data (time_collected, greenhouse_id, value) " +
                     "VALUES(?, ?, ?);";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, temperatureData.getTimeCollected());
            ps.setInt(2, greenhouseID);
            ps.setDouble(3, temperatureData.getValue());
            ps.execute();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private GreenhouseDTO getGreenhouse(int greenhouseID) {
        GreenhouseDTO greenhouseDTO = null;

        try {
            String getLightData = "SELECT * FROM greenhouse WHERE ? = id";
            PreparedStatement ps = connection.prepareStatement(getLightData);
            ps.setInt(1, greenhouseID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                String location = rs.getString("location");
                String name = rs.getString("name");
                Long dateCreated = rs.getLong("date_created");

                greenhouseDTO = new GreenhouseDTO(ipAddress, location, name, dateCreated, greenhouseID);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return greenhouseDTO;
    }

    private List<LightDTO> getLightData(int greenhouseID) {
        List<LightDTO> lightData = new ArrayList<>();

        try {
            String getLightData = "SELECT time_collected, red_value, blue_value FROM light_data WHERE ? = greenhouse_id";
            PreparedStatement ps = connection.prepareStatement(getLightData);
            ps.setInt(1, greenhouseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long timeCollected = rs.getLong("time_collected");
                int redValue = rs.getInt("red_value");
                int blueValue = rs.getInt("blue_value");

                LightDTO lightDTO = new LightDTO(timeCollected, redValue, blueValue);
                lightData.add(lightDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return lightData;
    }

    private List<HumidityDTO> getHumidityData (int greenhouseID) {
        List<HumidityDTO> humidityData = new ArrayList<>();

        try {
            String getLightData = "SELECT time_collected, value FROM humidity_data WHERE ? = greenhouse_id";
            PreparedStatement ps = connection.prepareStatement(getLightData);
            ps.setInt(1, greenhouseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long timeCollected = rs.getLong("time_collected");
                int value = rs.getInt("value");

                HumidityDTO humidityDTO = new HumidityDTO(timeCollected, value);
                humidityData.add(humidityDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return humidityData;
    }

    private List<TemperatureDTO> getTemperatureData (int greenhouseID) {
        List<TemperatureDTO> temperatureData = new ArrayList<>();

        try {
            String getLightData = "SELECT time_collected, value FROM temperature_data WHERE ? = greenhouse_id";
            PreparedStatement ps = connection.prepareStatement(getLightData);
            ps.setInt(1, greenhouseID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long timeCollected = rs.getLong("time_collected");
                int value = rs.getInt("value");

                TemperatureDTO temperatureDTO = new TemperatureDTO(timeCollected, value);
                temperatureData.add(temperatureDTO);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return temperatureData;
    }

    private boolean deleteHumiditySetpoint(int GreenhouseId) {
        String sql = "DELETE FROM humidity_setpoint WHERE ? = greenhouse_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, GreenhouseId);

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deleteTemperatureSetpoint(int GreenhouseId) {
        String sql = "DELETE FROM temperature_setpoint WHERE ? = greenhouse_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, GreenhouseId);

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean deleteLightSetpoint(int GreenhouseId) {
        String sql = "DELETE FROM light_setpoint WHERE ? = greenhouse_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, GreenhouseId);

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertHumiditySetpoint(int GreenhouseId, HumiditySetPoint humiditySetPoint) {
        String sql = "INSERT INTO humidity_setpoint (date_created, greenhouse_id, min, max, alarm_min, alarm_max)" +
                "     VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, System.currentTimeMillis());
            ps.setInt(2, GreenhouseId);
            ps.setDouble(3, humiditySetPoint.getMinValue());
            ps.setDouble(4, humiditySetPoint.getMaxValue());
            ps.setDouble(5, humiditySetPoint.getAlarmMinValue());
            ps.setDouble(6, humiditySetPoint.getAlarmMaxValue());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertTemperatureSetpoint(int GreenhouseId, TemperatureSetPoint temperatureSetPoint) {
        String sql = "INSERT INTO temperature_setpoint (date_created, greenhouse_id, min, max, alarm_min, alarm_max)" +
                "     VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, System.currentTimeMillis());
            ps.setInt(2, GreenhouseId);
            ps.setDouble(3, temperatureSetPoint.getMinValue());
            ps.setDouble(4, temperatureSetPoint.getMaxValue());
            ps.setDouble(5, temperatureSetPoint.getAlarmMinValue());
            ps.setDouble(6, temperatureSetPoint.getAlarmMaxValue());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertLightSetpoint(int GreenhouseId, LightSetPoint lightSetPoint) {
        String sql = "INSERT INTO temperature_setpoint (date_created, greenhouse_id, red, blue, start_time)" +
                "     VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, System.currentTimeMillis());
            ps.setInt(2, GreenhouseId);
            ps.setInt(3, lightSetPoint.getRedValue());
            ps.setInt(4, lightSetPoint.getBlueValue());
            ps.setString(5, lightSetPoint.getTime());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://tek-studsrv0e.stud-srv.sdu.dk:5432/greenhouse_data", "root", "root");
    }



}
