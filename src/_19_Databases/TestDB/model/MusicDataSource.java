package _19_Databases.TestDB.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDataSource {
    public static final String DB_NAME = "music.db";
    public static final String DB_PATH = "c:\\Users\\solnyshko\\Documents\\4kin\\java\\Projects\\Java Programming Masterclass for Software Developers\\src\\_19_Databases\\";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_PATH + DB_NAME;

    public static final String TABELE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final String TABELE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    public static final String TABELE_SONGS = "songs";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";

    private Connection connection;
    // жтог тест для  git

    public boolean open() {
        boolean flag = false;
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            flag = true;
        } catch (SQLException throwables) {
            System.out.println("Что пошло нетак" + throwables.getMessage());
            throwables.printStackTrace();
        }
        return flag;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("Неудачное отключение от сервера" + throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public List<Artist> queryArtist() {
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from " + TABELE_ARTISTS);

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(COLUMN_ARTISTS_ID));
                artist.setName(resultSet.getString(COLUMN_ARTISTS_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException sqlException) {
            System.out.println("неудачный запрос " + sqlException.getMessage());
            return null;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException sqlException) {
//                Парам парам
            }
        }
    }
}

