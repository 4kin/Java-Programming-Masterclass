package _19_Databases.TestDB;

import _19_Databases.TestDB.model.MusicDataSource;

public class MusicDB {
    public static void main(String[] args) {
        MusicDataSource dataSource = new MusicDataSource();
        if (!dataSource.open()){
            System.out.println("Неудалось подключиться к базеданных");
            return;
        }
        dataSource.close();
    }
}
