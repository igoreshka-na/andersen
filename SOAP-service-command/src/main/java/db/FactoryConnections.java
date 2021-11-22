package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FactoryConnections {
    private final String driver = "org.postgresql.Driver";
    private final String url = "jdbc:postgresql://127.0.0.1:5432/soapuser?useSSL=false&useUnicode=true&serverTimezone=UTC";
    private final String username = "igor";
    private final String password = "admadm45";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
