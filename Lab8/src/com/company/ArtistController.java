package com.company;

import java.sql.*;

public class ArtistController {
    private Connection dbConnection = null;

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void create(String name, String country) {
        String sql = "INSERT INTO artists (name, country) VALUES (?, ?)";
        int rowsInserted = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, country);

            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error on adding an artist!");
        }

        if (rowsInserted > 0) {
            System.out.println("A new artist added!");
        }
    }

    public void findByName(String name) {
        String sql = "SELECT * FROM artists WHERE name="+name;

        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                System.out.println("Name: " + result.getString(2) + " Country: " + result.getString(3) + "\n" );
            }
        } catch (SQLException e) {
            System.out.println("Error on find artist by name!");
        }
    }
}
