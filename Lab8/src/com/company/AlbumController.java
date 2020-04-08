package com.company;

import java.sql.*;

public class AlbumController {
    private Connection dbConnection = null;

    public void setDbConnection(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void create(String name, int artistId, int releaseYear) {
        String sql = "INSERT INTO albums (name, artist_id, release_year) VALUES (?, ?, ?)";
        int rowsInserted = 0;
        try {
            PreparedStatement statement = dbConnection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setInt(2, artistId);
            statement.setInt(3, releaseYear);

            rowsInserted = statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error on adding an album!");
        }

        if (rowsInserted > 0) {
            System.out.println("A new album added!");
        }
    }

    public void findByArtist(int artistId) {
        String sql = "SELECT * FROM albums WHERE artist_id="+artistId;

        try {
            Statement statement = dbConnection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while(result.next()) {
                System.out.println("Name: " + result.getString(2) + " Release year: " + result.getString(3) + "\n" );
            }
        } catch (SQLException e) {
            System.out.println("Error on find album by artist!");
        }
    }
}
