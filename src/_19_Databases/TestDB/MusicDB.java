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
            System.out.println("�� ������� ������������ � ���� ������");
            return;
        }

        System.out.println("\n������ �� ��������\n");
        List<Artist> artists = dataSource.queryArtist();
        for (Artist artist : artists) {
            System.out.println("ID = " + artist.getId() + "\t Name = " + artist.getName());
        }

        System.out.println("\n������ �� ����� ������\n");
        List<String> albumsForArtist = dataSource.queryAlbumsForArtists("Iron Maiden", 1);

        for (String album : albumsForArtist) {
            System.out.println(album);
        }

        System.out.println("\n������ �� �������� �����\n");
        List<SongArtist> songArtists = dataSource.queryArtistsForSong("1. The Gnome", 1);
        if (songArtists == null) {
            System.out.println("������� ������");
            return;
        }
        for (SongArtist songArtist : songArtists) {
            System.out.println("������  = " + songArtist.getArtistName() + "\t ������ = " + songArtist.getAlbumName() + "\t ����� ����� = " + songArtist.getTrack());
        }

        System.out.println("\n������ �� ��������\n");

        int count = dataSource.getCount(MusicDataSource.TABELE_ALBUMS);
        System.out.println("���������� ������� � ������� = " + count);

        System.out.println("\n������ �� �������� � ����������������� �������� \n");

        int countPreparedStatment = dataSource.getCountPreparedStatment(MusicDataSource.TABELE_ALBUMS, 16);
        System.out.println("���������� ������� � ������� = " + countPreparedStatment);


        System.out.println("\n������� ������ ������� \n");

        try {
            String artistName = "������ ��� ����� ����������";
            int idForNewArtist = dataSource.insertArtist(artistName);
            System.out.println("������ "+artistName+" �������� � �������, id ������ = "+ String.valueOf(idForNewArtist));
            // TODO ������� ��������� ����������� ������ ������
//            System.out.format("������ %d �������� � �������, id ������ = %d", artistName, idForNewArtist);


        } catch (SQLException sqlException) {
            System.out.println("��� �� ����� ��� ��� ������� ������ " + sqlException.getMessage());
            sqlException.printStackTrace();
        }

        dataSource.close();

        int i = 11 ;
        // ��� �������� ������ � amend

    }
}
