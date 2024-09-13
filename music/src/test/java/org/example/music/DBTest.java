package org.example.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DBTest {
    @BeforeAll
    public static void OpenDatabase() {
        org.example.database.DatabaseConnection.connect("songs.db","connection");
    }
    @Test
    public void testRead() {
            Optional<Song> song = Song.Persistence.read(1);
            assertTrue(song.isPresent());
    }

    @ParameterizedTest
    @CsvFileSource (
            files = "songs.csv",
            useHeadersInDisplayName = true
    )
    void CSVindex(int id, String artist, String title, int duration){
        Optional<Song> song = Song.Persistence.read(id);
        Song csvsong =  new Song(artist,title,duration);
        assertTrue(csvsong,song.orElse(null));

    }

    @AfterAll
    public static void CloseDatabase() {
        org.example.database.DatabaseConnection.disconnect("connection");
    }
}
