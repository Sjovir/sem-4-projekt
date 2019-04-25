package org.grp2.gms.dao;

import org.grp2.gms.common.*;

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

    public List<GreenhouseDTO> getGreenhouseObjects() {
        List<GreenhouseDTO> greenhouses = getGreenhouses();

        return greenhouses;
    }

    public HumiditySetpointDTO getHumiditySetpointObject(int greenhouseID) {
        HumiditySetpointDTO humiditySetpointDTO = getHumiditySetpoint(greenhouseID);

        return humiditySetpointDTO;
    }

    public TemperatureSetpointDTO getTemperatureSetpointObject(int greenhouseID) {
        TemperatureSetpointDTO temperatureSetpointDTO = getTemperatureSetpoint(greenhouseID);

        return temperatureSetpointDTO;
    }

    public List<LightSetpointDTO> getLightSetpointObjects(int greenhouseID) {
        List<LightSetpointDTO> lightSetpointDTOList = getLightSetpoint(greenhouseID);

        return lightSetpointDTOList;
    }

    public boolean writeCollectedData (int greenhouseID, LightDTO lightData, HumidityDTO humidityData,
                                       TemperatureDTO temperatureData) {
        if (insertLightData(greenhouseID, lightData) && insertHumidityData(greenhouseID, humidityData) &&
                insertTemperatureData(greenhouseID, temperatureData))
            return true;
        else
            return false;
    }

    public boolean writeHumiditySetpoint(int GreenhouseId, HumiditySetpointDTO humiditySetPointDTO) {

        if(deleteHumiditySetpoint(GreenhouseId) && insertHumiditySetpoint(GreenhouseId, humiditySetPointDTO)) {
            return true;
        } else
           return false;
    }

    public boolean writeTemperatureSetpoint(int GreenhouseId, TemperatureSetpointDTO temperatureSetPointDTO) {

        if(deleteTemperatureSetpoint(GreenhouseId) && insertTemperatureSetpoint(GreenhouseId, temperatureSetPointDTO)) {
            return true;
        } else
            return false;
    }

    public boolean writeLightSetpoint(int GreenhouseId, LightSetpointDTO lightSetPointDTO) {

        if(deleteLightSetpoint(GreenhouseId, lightSetPointDTO.getStartTime()) && insertLightSetpoint(GreenhouseId, lightSetPointDTO)) {
            return true;
        } else
            return false;
    }

    public int writeGreenhouse(GreenhouseDTO greenhouseDTO) {

        return insertGreenhouse(greenhouseDTO);
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
                int port = rs.getInt("port");
                String location = rs.getString("location");
                String name = rs.getString("name");
                Long dateCreated = rs.getLong("date_created");

                greenhouseDTO = new GreenhouseDTO(ipAddress, port, location, name, dateCreated, greenhouseID);
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

    private boolean deleteLightSetpoint(int GreenhouseId, String startTime) {
        String sql = "DELETE FROM light_setpoint WHERE ? = greenhouse_id AND ? = start_time";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, GreenhouseId);
            ps.setString(2, startTime);

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertHumiditySetpoint(int GreenhouseId, HumiditySetpointDTO humiditySetPointDTO) {
        String sql = "INSERT INTO humidity_setpoint (date_created, greenhouse_id, min, max, alarm_min, alarm_max)" +
                "     VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, humiditySetPointDTO.getDateCreated());
            ps.setInt(2, GreenhouseId);
            ps.setDouble(3, humiditySetPointDTO.getMin());
            ps.setDouble(4, humiditySetPointDTO.getMax());
            ps.setDouble(5, humiditySetPointDTO.getAlarmMin());
            ps.setDouble(6, humiditySetPointDTO.getAlarmMax());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertTemperatureSetpoint(int GreenhouseId, TemperatureSetpointDTO temperatureSetPointDTO) {
        String sql = "INSERT INTO temperature_setpoint (date_created, greenhouse_id, min, max, alarm_min, alarm_max)" +
                "     VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, temperatureSetPointDTO.getDateCreated());
            ps.setInt(2, GreenhouseId);
            ps.setDouble(3, temperatureSetPointDTO.getMin());
            ps.setDouble(4, temperatureSetPointDTO.getMax());
            ps.setDouble(5, temperatureSetPointDTO.getAlarmMin());
            ps.setDouble(6, temperatureSetPointDTO.getAlarmMax());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean insertLightSetpoint(int GreenhouseId, LightSetpointDTO lightSetPointDTO) {
        String sql = "INSERT INTO light_setpoint (date_created, greenhouse_id, red, blue, start_time)" +
                "     VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setLong(1, lightSetPointDTO.getDateCreated());
            ps.setInt(2, GreenhouseId);
            ps.setInt(3, lightSetPointDTO.getRed());
            ps.setInt(4, lightSetPointDTO.getBlue());
            ps.setString(5, lightSetPointDTO.getStartTime());

            ps.execute();

            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private List<GreenhouseDTO> getGreenhouses() {
        List<GreenhouseDTO> greenhouseList = new ArrayList<>();
        String sql = "SELECT * FROM greenhouse";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int id = rs.getInt("id");
                String ip = rs.getString("ip_address");
                int port = rs.getInt("port");
                String location = rs.getString("location");
                String name = rs.getString("name");
                Long dateCreated = rs.getLong("date_created");

                GreenhouseDTO greenhouse = new GreenhouseDTO(ip, port, location, name, dateCreated, id);
                greenhouseList.add(greenhouse);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return greenhouseList;
    }

    private HumiditySetpointDTO getHumiditySetpoint(int greenhouseID) {
        HumiditySetpointDTO humiditySetpointDTO = null;
        String sql = "SELECT * FROM humidity_setpoint WHERE greenhouse_id = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, greenhouseID);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                long dateCreated = rs.getLong("date_created");
                int id = rs.getInt("greenhouse_id");
                double min = rs.getDouble("min");
                double max = rs.getDouble("max");
                double alarmMin = rs.getDouble("alarm_min");
                double alarmMax = rs.getDouble("alarm_max");

                humiditySetpointDTO = new HumiditySetpointDTO(dateCreated, id, min, max, alarmMin, alarmMax);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return  humiditySetpointDTO;
    }

    private TemperatureSetpointDTO getTemperatureSetpoint(int greenhouseID) {
        TemperatureSetpointDTO temperatureSetpointDTO = null;
        String sql = "SELECT * FROM temperature_setpoint WHERE ? = greenhouse_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, greenhouseID);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                long dateCreated = rs.getLong("date_created");
                int id = rs.getInt("greenhouse_id");
                double min = rs.getDouble("min");
                double max = rs.getDouble("max");
                double alarmMin = rs.getDouble("alarm_min");
                double alarmMax = rs.getDouble("alarm_max");

                temperatureSetpointDTO = new TemperatureSetpointDTO(dateCreated, id, min, max, alarmMin, alarmMax);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }
        return  temperatureSetpointDTO;
    }

    private List<LightSetpointDTO> getLightSetpoint(int greenhouseID) {
        List<LightSetpointDTO> lightSetpointDTOList = new ArrayList<>();
        String sql = "SELECT * FROM light_setpoint WHERE ? = greenhouse_id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, greenhouseID);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                long dateCreated = rs.getLong("date_created");
                int id = rs.getInt("greenhouse_id");
                int red = rs.getInt("red");
                int blue = rs.getInt("blue");
                String startTime = rs.getString("start_time");

                LightSetpointDTO lightSetpointDTO = new LightSetpointDTO(dateCreated, id, red, blue, startTime);

                lightSetpointDTOList.add(lightSetpointDTO);

            }

        } catch(SQLException e) {
            e.printStackTrace();
        }

        return lightSetpointDTOList;
    }

    private int insertGreenhouse(GreenhouseDTO greenhouseDTO) {
        String sql = "INSERT INTO greenhouse (ip_address, port, location, name, date_created) VALUES (?, ?, ?, ?, ?) RETURNING id";

        try {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, greenhouseDTO.getIpAddress());
            ps.setInt(2, greenhouseDTO.getPort());
            ps.setString(3, greenhouseDTO.getLocation());
            ps.setString(4, greenhouseDTO.getName());
            ps.setLong(5, greenhouseDTO.getDateCreated());

            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();

            if(rs.next()) {
                int id = rs.getInt(1);
                return id;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return -1;

    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://tek-studsrv0e.stud-srv.sdu.dk:5432/greenhouse_data", "root", "root");
    }



}
