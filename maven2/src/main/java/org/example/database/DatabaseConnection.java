package org.example.database;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private java.sql.Connection connection;

    public java.sql.Connection getConnection() {
        return connection;
    }
    public void connect(String path) {
        try{
            connection = DriverManager.getConnection(String.format("jdbc:sqlite:%s", path));
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect()  {
        try{
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            System.out.println("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
