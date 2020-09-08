package P2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    private Connection connection;

    public Connect() throws SQLException {
        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "Hooibaal";

        connection = DriverManager.getConnection(dbUrl, user, pass);
    }

    public Connection getConnection() {
        return connection;
    }
}
