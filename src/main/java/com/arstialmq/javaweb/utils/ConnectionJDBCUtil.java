package com.arstialmq.javaweb.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionJDBCUtil {
    static final String DB_URL = "jdbc:mysql://localhost:3306/estatebasic?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String DB_USER = "root";
    static final String DB_PASS = "Root@1234";

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
