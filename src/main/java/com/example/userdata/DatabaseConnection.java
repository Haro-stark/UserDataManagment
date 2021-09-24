package com.example.userdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance = new DatabaseConnection();
    public static final String URL = "jdbc:mysql://localhost:3306/UserInfo";
    public static final String USER = "root";
    public static final String PASSWORD = "123456";
    public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
//    final String[] args = new String[]{"-tcpPort", "8092", "-ifNotExists", "true"};

    //private constructor
    private DatabaseConnection() {
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Unable to Connect to Database.");
        }
        return connection;
    }

    public static Connection getConnection() {
        return instance.createConnection();
    }

    public static void endConnection(Connection con) {
        instance.closeConnection(con);
    }

    private void closeConnection(Connection con) {
        try {
            con.close();
        } catch (SQLException e) {
            System.out.println("Error Closing connection...");
        }
    }


}
