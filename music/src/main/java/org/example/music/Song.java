package org.example.music;


import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

public class Song {
    public String title;
    public String Artist;
    public int duration;

    public Song(String Artist, String title, int duration){
        this.duration = duration;
        this.Artist = Artist;
        this.title = title;
    }

    public static class Persistence{
        static Optional<Song> read(int id){
            Connection c = org.example.database.DatabaseConnection.getConnection("connection");
            String sql = "SELECT artist, title, duration FROM songs WHERE id = ?";
            try {
                    PreparedStatement statement = org.example.database.DatabaseConnection.getConnection().prepareStatement(sql);
                    statement.setInt(1, id);
                    ResultSet resultSet = statement.executeQuery();
                if(resultSet.next()) {
                    return Optional.of(new Song(
                            resultSet.getString("artist"),
                            resultSet.getString("title"),
                            resultSet.getInt("length")
                    ));
                }
                return Optional.empty();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Song song = (Song) o;
        return duration == song.duration && Objects.equals(title, song.title) && Objects.equals(Artist, song.Artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, Artist, duration);
    }

    public int getDuration() {
        return duration;
    }
}

