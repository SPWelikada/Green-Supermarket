package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Supermarket";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
