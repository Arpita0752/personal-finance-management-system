package com.finance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private static final String URL = "jdbc:oracle:thin:@localhost:1521:orcl123";
    
    private static final String USERNAME = System.getenv("scott");
    private static final String PASSWORD = System.getenv("tiger");

    
    public static Connection getConnection() throws SQLException {

        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
    
    public static void main(String[] args) {

        try {
            Connection connection = getConnection();
            System.out.println("Database Connected Successfully!");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}