package dbExample;


import java.sql.*;

public class DbExample {

    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager
                    .getConnection("jdbc:h2:file:C:\\полный путь к базе через двойные слэши");
            Statement statement = connection.createStatement();
            //statement.executeUpdate("create table table2(id int)");
//            statement.executeUpdate("insert into table2 (id) values (11)");
//            statement.executeUpdate("insert into table2 (id) values (22)");
//            statement.executeUpdate("insert into table2 (id) values (33)");

            ResultSet rs = statement.executeQuery("select * from table2");

            while (rs.next()) {
                System.out.println(rs.getInt(rs.findColumn("id")));
            }

            statement.close();
            connection.close();

        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }
}
