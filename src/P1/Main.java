package P1;

import java.sql.*;
import java.text.MessageFormat;

public class Main {
    public static void main(String[] args) throws SQLException {
        int i = 0;

        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "Hooibaal";

        Connection myConn = DriverManager.getConnection(dbUrl, user, pass);

        Statement myStmt = myConn.createStatement();
        ResultSet myRs = myStmt.executeQuery("select * from reiziger");

        while (myRs.next()) {
            i++;

            String vrlt = myRs.getString("voorletters");
            String tus = myRs.getString("tussenvoegsel");
            String anm = myRs.getString("achternaam");
            String dat = myRs.getString("geboortedatum");

            if (tus != null) {
                System.out.println(MessageFormat.format("{0}.\t {1} {2} {3}\t: {4};", i, vrlt, tus, anm, dat));
            } else {
                System.out.println(MessageFormat.format("{0}.\t {1} {2}\t: {3};", i, vrlt, anm, dat));
            }
        }
    }
}