package artdealer.SQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateSingleInstance {
    static final String DB_URL = "jdbc:mysql://localhost:3306/artshop";
    static final String USER = "root";
    static final String PASSWORD = "aggY836^@zT9&";

    public static Connection ConnectDB() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        return conn;
    }
}