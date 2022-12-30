package _19_Databases.TestDB;

import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class testDB_CRUD {

    public static final String DB_NAME = "c:\\Users\\solnyshko\\Documents\\4kin\\java\\Projects\\Java Programming Masterclass for Software Developers\\src\\_19_Databases\\music.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = connection.createStatement();

            statement.execute("drop table if exists " + TABLE_CONTACTS);
            statement.execute("create table if not exists " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text" +
                    ")");

            inserts(statement);
            ResultSet resultSet = statement.executeQuery("select * from " + TABLE_CONTACTS);

            while (resultSet.next()) {
                System.out.println(resultSet.getString(COLUMN_NAME) + "  " + resultSet.getInt(COLUMN_PHONE) + "   " + resultSet.getString(COLUMN_EMAIL));
            }

        } catch (SQLException throwables) {
            System.out.println("Чтото пощло нетак " + throwables.getMessage());
            throwables.printStackTrace();
        }

    }

    private static void inserts(Statement statement) throws SQLException {
        insertContacts(statement, "Никита", 12312556, "эмаил никита");
        insertContacts(statement, "Женя", 23446, "эмаил жени");
        insertContacts(statement, "Васелина", 12312556, "эмаил васелины");
    }

    private static void insertContacts(Statement statement, String name, Integer phone, String email) throws SQLException {
        statement.execute("insert into " + TABLE_CONTACTS + " (" + COLUMN_NAME + "," + COLUMN_PHONE + "," + COLUMN_EMAIL + ") " + " values ('" + name + "', '" + phone + "', '" + email + "')");
    }
}
