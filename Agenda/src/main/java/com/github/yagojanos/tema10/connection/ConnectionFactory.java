package com.github.yagojanos.tema10.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/agenda";
        String username = "root";
        String password = "root";

        return DriverManager.getConnection(url, username, password);
    }
}
