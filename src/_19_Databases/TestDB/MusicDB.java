package _19_Databases.TestDB;

import _19_Databases.TestDB.model.Artist;
import _19_Databases.TestDB.model.MusicDataSource;
import _19_Databases.TestDB.model.SongArtist;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MusicDB {
    public static void main(String[] args) {
        MusicDataSource dataSource = new MusicDataSource();
        if (!dataSource.open()) {
            System.out.println("Не удалось подключиться к базе данных");
            return;
        }

        System.out.println("\nЗапрос по АРТИСТАМ\n");
        List<Artist> artists = dataSource.queryArtist();
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + "\t Name = " + artist.getName());
        }

        System.out.println("\nЗапрос по Айрон Майден\n");
        List<String> albumsForArtist = dataSource.queryAlbumsForArtists("Iron Maiden", 1);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        System.out.println("\nЗапрос по Названия песни\n");
        List<SongArtist> songArtists = dataSource.queryArtistsForSong("1. The Gnome", 1);
        if (songArtists == null) {
            System.out.println("Вернуло пустое");
            return;
        }
        for (SongArtist songArtist : songArtists) {
            System.out.println("Артист  = " + songArtist.getArtistName() + "\t Альбом = " + songArtist.getAlbumName() + "\t Номер трека = " + songArtist.getTrack());
        }

        System.out.println("\nЗапрос по счетчику\n");

        int count = dataSource.getCount(MusicDataSource.TABELE_ALBUMS);
        System.out.println("Количество записей в таблице = " + count);

        System.out.println("\nЗапрос по счетчику с параметризованным запросом \n");

        int countPreparedStatment = dataSource.getCountPreparedStatment(MusicDataSource.TABELE_ALBUMS, 16);
        System.out.println("Количество записей в таблице = " + countPreparedStatment);


        System.out.println("\nВставка нового артиста \n");

        try {
            String artistName = "Артист для пробы добавления";
            int idForNewArtist = dataSource.insertArtist(artistName);
            System.out.println("артист "+artistName+" добавлен в таблицу, id записи = "+ String.valueOf(idForNewArtist));
            // TODO Сделать правильно разобраться почему ошибка
//            System.out.format("артист %d добавлен в таблицу, id записи = %d", artistName, idForNewArtist);


        } catch (SQLException sqlException) {
            System.out.println("что то пошло нет при вставке записи " + sqlException.getMessage());
            sqlException.printStackTrace();
        }

        dataSource.close();

        int i = 11 ;
        // это проверка комита с amend

    }
}
