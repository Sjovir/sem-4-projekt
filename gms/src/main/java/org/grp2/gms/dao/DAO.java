package org.grp2.gms.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {

    private Connection connection;

    public DAO() {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:postgresql://tek-studsrv0e.stud-srv.sdu.dk:5432/greenhouse_data", "root", "root");
        return DriverManager.getConnection("jdbc:postgresql://10.123.3.53:5432/greenhouse_data", "root", "root");
    }



}
