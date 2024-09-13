package org.example.auth;
import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountManager {
    DatabaseConnection dbConnection;

    public AccountManager(DatabaseConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void init(){
        try {
            String createSQLTable =
                    "CREATE TABLE IF NOT EXISTS accounts( " +
                            "id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                            "username TEXT NOT NULL," +
                            "password TEXT NOT NULL)";

            PreparedStatement preparedStatement = dbConnection.getConnection()
                    .prepareStatement(createSQLTable);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean register(String username, String password){
        try {
            Account acc = getAccount(username);
            if(acc==null){
                return false;
            }
            String insertSQL = "INSERT INTO accounts (username, password) VALUES (?, ?)";
            Connection connection = dbConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public boolean authenticate(String username, String password){
        String query = "SELECT password FROM accounts WHERE username = ?";
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                String dbPassword = resultSet.getString("password");
                if(dbPassword.equals(password)){
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public Account getAccount(String username){
        String Query = "SELECT id, username FROM accounts WHERE username = ?";
        try {
            Connection connection = dbConnection.getConnection();
            PreparedStatement userStatement = connection.prepareStatement(Query);
            userStatement.setString(1, username);
            ResultSet results = userStatement.executeQuery();
            if(results.next()){
                int id = results.getInt("id");
                String username1 = results.getString("username");
                return new Account(username1, id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
