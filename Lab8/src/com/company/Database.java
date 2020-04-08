package com.company;

        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.sql.SQLException;

public class Database {
    private Connection dbConnection = null;

    public Connection getDbConnection() {
        return dbConnection;
    }

    public Database(String url, String user, String password) {
        try {
            dbConnection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Error on connection!");
        }
    }

    public void close() {
        try {
            dbConnection.close();
        } catch (SQLException e) {
            System.out.println("Error on closing the connection!");
        }
    }
}
