package _19_Databases.TestDB;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class mainDB {
    public static final String DB_NAME = "c:\\Users\\solnyshko\\Documents\\4kin\\java\\Projects\\Java Programming Masterclass for Software Developers\\src\\_19_Databases\\music.db";

    public static void main(String[] args) {
        try {

            Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            conn.setAutoCommit(false);
            Statement statement = conn.createStatement();
            statement.execute("create table if not exists contacts (name text,phone int,email text)");

//            statement.execute("INSERT into contacts (name,phone,email) values ('������','981273','����� ������')");
//            statement.execute("INSERT into contacts (name,phone,email) values ('����','2323477','����� ����')");
//            statement.execute("INSERT into contacts (name,phone,email) values ('����','981273','����� ����')");

//            statement.execute("select * from contacts");
//            ResultSet resultSet = statement.getResultSet();


            ResultSet resultSet = statement.executeQuery("select * from contacts");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("name") + "  " + resultSet.getInt("phone") + "   " + resultSet.getString("email"));
            }
            resultSet.close();
            conn.commit();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("��� ����� ����� " + e.getMessage());
        }

    }
}
