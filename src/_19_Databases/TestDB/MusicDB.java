package _19_Databases.TestDB;

import _19_Databases.TestDB.model.Artist;
import _19_Databases.TestDB.model.MusicDataSource;
import _19_Databases.TestDB.model.SongArtist;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class MusicDB {
    public static void main(String[] args) {
        MusicDataSource dataSource = new MusicDataSource();
        if (!dataSource.open()) {
            System.out.println("Неудалось подключиться к базеданных");
            return;
        }

        System.out.println("\nЗапрос по АРТИСТАМ\n");
        List<Artist> artists = dataSource.queryArtist();
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + "\t Name = " + artist.getName());
        }

        System.out.println("\nЗвапрос по Айрон Майден\n");
        List<String> albumsForArtist = dataSource.queryAlbumsForArtists("Iron Maiden", 1);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        System.out.println("\nЗвапрос по Названия песни\n");
        List<SongArtist> songArtists = dataSource.queryArtistsForSong("1. The Gnome", 1);
        if (songArtists == null) {
            System.out.println("Вернуло пустое");
            return;
        }
        for (SongArtist songArtist : songArtists) {
            System.out.println("Аритст  = " + songArtist.getArtistName() + "\t Альбом = " + songArtist.getAlbumName() + "\t Номер трека = " + songArtist.getTrack());
        }

        System.out.println("\nЗвапрос по счетчику\n");

        int count = dataSource.getCount(MusicDataSource.TABELE_ALBUMS);
        System.out.println("Количество записей в таблице = " + count);

        System.out.println("\nЗвапрос по счетчику с параметризованным запросом \n");

        int countPreparedStatment = dataSource.getCountPreparedStatment(MusicDataSource.TABELE_ALBUMS,16);
        System.out.println("Количество записей в таблице = " + countPreparedStatment);

        dataSource.close();
    }
}
