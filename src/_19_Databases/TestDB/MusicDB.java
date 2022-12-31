package _19_Databases.TestDB;

import _19_Databases.TestDB.model.Artist;
import _19_Databases.TestDB.model.MusicDataSource;

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
            System.out.println("ID = " + artist.getId() + " Name = " + artist.getName());
        }

        System.out.println("\nЗвапрос по Айрон Майден\n");
        List<String> albumsForArtist = dataSource.queryAlbumsForArtists("Iron Maiden", 1);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }



        dataSource.close();
    }
}
