package _19_Databases.TestDB.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDataSource {
    public static final String DB_NAME = "music.db";
    //    c:\Users\4kin\_java_porjects\Java Programming Masterclass for Software Developers\src\_19_Databases\
//    c:\Users\solnyshko\Documents\4kin\java\Projects\Java Programming Masterclass for Software Developers\src\_19_Databases\
    public static final String DB_PATH = "\\\\ULTRA-SLIM-4KIN\\4kin-c\\_java_projects\\Java Programming Masterclass for Software Developers\\src\\_19_Databases\\";


    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_PATH + DB_NAME;

    public static final String TABELE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";
    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABELE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";
    public static final int INDEX_ARTIST_ID = 1;
    public static final int INDEX_ARTIST_NAME = 2;

    public static final String TABELE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;


    public static final String QUERY_ARTIST_FORSONG_START =
            "select " + TABELE_ARTISTS + "." + COLUMN_ARTISTS_NAME + ", " +
                    TABELE_ALBUMS + "." + COLUMN_ALBUM_NAME + ", " +
                    TABELE_SONGS + "." + COLUMN_SONGS_TRACK + " from " + TABELE_SONGS +
                    " inner join " + TABELE_ALBUMS + " on " +
                    TABELE_SONGS + "." + COLUMN_SONGS_ALBUM + " = " + TABELE_ALBUMS + "." + COLUMN_ALBUM_ID +
                    " inner join " + TABELE_ARTISTS + " ON " +
                    TABELE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABELE_ARTISTS + "." + COLUMN_ARTISTS_ID +
                    " where " + TABELE_SONGS + "." + COLUMN_SONGS_TITLE + " = \"";
    public static final String QUERY_ARTIST_FOR_SONG_SORT = " order by " + TABELE_ARTISTS + "." + COLUMN_ARTISTS_NAME + ", " +
            TABELE_ALBUMS + "." + COLUMN_ALBUM_NAME + " collate nocase ";


    private Connection connection;
    // ���� ���� ���  git

    public boolean open() {
        boolean flag = false;
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            flag = true;
        } catch (SQLException throwables) {
            System.out.println("��� ����� �����" + throwables.getMessage());
            throwables.printStackTrace();
        }
        return flag;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            System.out.println("��������� ���������� �� �������" + throwables.getMessage());
            throwables.printStackTrace();
        }
    }

    public List<Artist> queryArtist() {

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("select * from " + TABELE_ARTISTS)
        ) {

            List<Artist> artists = new ArrayList<>();
            while (resultSet.next()) {
                Artist artist = new Artist();
                artist.setId(resultSet.getInt(COLUMN_ARTISTS_ID));
                artist.setName(resultSet.getString(COLUMN_ARTISTS_NAME));
                artists.add(artist);
            }
            return artists;
        } catch (SQLException sqlException) {
            System.out.println("��������� ������ " + sqlException.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtists(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT ");
        sb.append(TABELE_ALBUMS + "." + COLUMN_ALBUM_NAME);
        sb.append(" FROM " + TABELE_ALBUMS + " INNER JOIN " + TABELE_ARTISTS);
        sb.append(" ON " + TABELE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABELE_ARTISTS + "." + COLUMN_ARTISTS_ID);
        sb.append(" WHERE " + TABELE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " = \"" + artistName + "\"");

        System.out.println("������ = " + sb.toString());

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sb.toString());
        ) {
            List<String> albums = new ArrayList<>();
            while (resultSet.next()) {
                albums.add(resultSet.getString(COLUMN_ALBUM_NAME));
            }
            return albums;

        } catch (SQLException sqlException) {
            System.out.println("������ ��������� " + sqlException.getMessage());
            sqlException.printStackTrace();
            return null;
        }
    }

    public List<SongArtist> queryArtistsForSong(String songName, int sortOrder) {
        StringBuilder stringBuilder = new StringBuilder(QUERY_ARTIST_FORSONG_START);
        stringBuilder.append(songName);
        stringBuilder.append("\"");
        if (sortOrder != 0) {
            stringBuilder.append(QUERY_ARTIST_FOR_SONG_SORT);
            stringBuilder.append("asc");
        }
        System.out.println("������ SQL = " + stringBuilder.toString());

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(stringBuilder.toString())) {
            List<SongArtist> songArtists = new ArrayList<>();
            while (resultSet.next()) {
                SongArtist songArtist = new SongArtist();
                songArtist.setArtistName(resultSet.getString(1));
                songArtist.setAlbumName(resultSet.getString(2));
                songArtist.setTrack(resultSet.getInt(3));
                songArtists.add(songArtist);
            }
            return songArtists;
        } catch (SQLException sqlException) {
            System.out.println("��� �� ����� ����� " + sqlException.getMessage());
            sqlException.printStackTrace();
            return null;
        }
    }

    public int getCount(String table) {
        String sql = "SELECT count(*) as count from " + table;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql);) {
            int count = resultSet.getInt("count");
            System.out.format("������� = %d \n", count);
            return count;
        } catch (SQLException sqlException) {
            System.out.println("����� ����� ����� " + sqlException.getMessage());
            sqlException.printStackTrace();
            return -1;
        }
    }

    public int getCountPreparedStatment(String table, int artistId) {
        try {

            PreparedStatement getCountPreparedStatement = connection.prepareStatement("SELECT count(*) as count from " + table + " where artist = ?");
            getCountPreparedStatement.setInt(1, artistId);
            ResultSet resultSet = getCountPreparedStatement.executeQuery();
            return resultSet.getInt("count");

        } catch (SQLException sqlException) {
            System.out.println("����� ����� ����� " + sqlException.getMessage());
            sqlException.printStackTrace();
            return -1;
        }
    }

    public int insertArtist(String artistName) throws SQLException {
        PreparedStatement insertArtistPreparedStatement = connection.prepareStatement("insert into artists (name) values (?)");
        insertArtistPreparedStatement.setString(1, artistName);
        int affectedRows = insertArtistPreparedStatement.executeUpdate();
        if (affectedRows != 1) {
            throw new SQLException("������ �� ����������");
        }
        ResultSet generatedKeys = insertArtistPreparedStatement.getGeneratedKeys();
        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);

        } else {
            throw new SQLException("������ �������� ID ������");
        }


    }
}

