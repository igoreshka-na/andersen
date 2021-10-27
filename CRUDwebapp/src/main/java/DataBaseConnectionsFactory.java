import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseConnectionsFactory {
    private static final String NAMEDB = "andersencrud";
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String USER = "root";
    private static final String PASSWORD = "admadm45";
    private final DataSource base;

    public DataBaseConnectionsFactory() {
        MysqlDataSource base = new MysqlDataSource();

        base.setDatabaseName(NAMEDB);
        base.setServerName(SERVER);
        base.setPort(PORT);
        base.setUser(USER);
        base.setPassword(PASSWORD);

        this.base = base;
    }

    public static Connection getConnection() throws SQLException {
        return SingletonHelper.INSTANCE.base.getConnection();
    }

    private static class SingletonHelper {
        private static final DataBaseConnectionsFactory INSTANCE = new DataBaseConnectionsFactory();
    }
}
