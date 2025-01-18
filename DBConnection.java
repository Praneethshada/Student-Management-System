package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    // Replace these with your database details
    private static final String URL = "jdbc:mysql://localhost:3306/student_management"; // Database URL
    private static final String USER = "root"; // Your MySQL username
    private static final String PASSWORD = "mysqlpass"; // Your MySQL password

    // Method to establish the connection
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
