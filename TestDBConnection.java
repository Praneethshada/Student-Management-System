package utils;

import java.sql.Connection;

public class TestDBConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DBConnection.getConnection();
            System.out.println("Connection successful: " + (conn != null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
