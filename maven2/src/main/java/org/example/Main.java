package org.example;
import org.example.auth.AccountManager;
import org.example.database.DatabaseConnection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseConnection dbConnection = new DatabaseConnection();
        dbConnection.connect("database.db");
        AccountManager accManager = new AccountManager(dbConnection);
        accManager.init();
        accManager.register("lukasz","12234");
        var temp = accManager.getAccount("lukasz");
        System.out.println(temp);
        dbConnection.disconnect();

    }
}