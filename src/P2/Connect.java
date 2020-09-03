package P2;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
    protected static Connection connect() throws Exception {
        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "Hooibaal";

        Connection myConn = DriverManager.getConnection(dbUrl, user, pass);

        return myConn;
    }
}
