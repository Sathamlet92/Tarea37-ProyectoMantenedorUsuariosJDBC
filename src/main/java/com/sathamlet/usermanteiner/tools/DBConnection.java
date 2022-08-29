package com.sathamlet.usermanteiner.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static String stringConn = "omitted";
    private static String userName = "omitted";
    private static String password = "omitted";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(stringConn, userName, password);
    }
}
