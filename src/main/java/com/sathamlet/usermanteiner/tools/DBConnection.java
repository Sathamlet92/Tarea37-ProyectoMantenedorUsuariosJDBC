package com.sathamlet.usermanteiner.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String stringConn = "jdbc:mysql://127.0.0.1:3306/Java_course?serverTimeZone=America/Mexico_City";
    private static String userName = "omitted";
    private static String password = "omitted";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(stringConn, userName, password);
    }
}
