package P2;

import java.sql.*;
import java.text.MessageFormat;

public class ReizigerDAO {
    private static Connection connect() throws Exception {
        String dbUrl = "jdbc:postgresql://localhost:5432/ovchip";
        String user = "postgres";
        String pass = "Hooibaal";

        Connection myConn = DriverManager.getConnection(dbUrl, user, pass);

        return myConn;
    }

    private static void stringify(ResultSet myRs) throws SQLException {
        String id = myRs.getString("reiziger_id");
        String vrlt = myRs.getString("voorletters");
        String tus = myRs.getString("tussenvoegsel");
        String anm = myRs.getString("achternaam");
        String dat = myRs.getString("geboortedatum");

        if (tus != null) {
            System.out.println(MessageFormat.format("{0}.\t {1} {2} {3}\t: {4};", id, vrlt, tus, anm, dat));
        } else {
            System.out.println(MessageFormat.format("{0}.\t {1} {2}\t: {3};", id, vrlt, anm, dat));
        }
    }

    public static void readReizigerByID(int id) {
        try {
            Statement myStmt = connect().createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE reiziger_id = " + id);
            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readReizigerByGebDatum(Date gebDatum) {
        try {
            Statement myStmt = connect().createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE geboortedatum = '" + gebDatum +"';");
            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void readAllReiziger() {
        try {
            Statement myStmt = connect().createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger");

            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void createReiziger(Reiziger reiziger) {
        try {
            Statement myStmt = connect().createStatement();

            String sql = "INSERT INTO reiziger (reiziger_id, voorletters, tussenvoegsel, achternaam, geboortedatum) " +
                    "VALUES ("+ reiziger.getId() + ", '" + reiziger.getVoorletters() + "', ";
            if (reiziger.getTussenvoegsel() != null) {
                sql += "'" + reiziger.getTussenvoegsel() + "'";
            } else {
                sql += reiziger.getTussenvoegsel();
            }
            sql += ", '" + reiziger.getAchternaam() +"', '"+ reiziger.getGeboortedatum() + "');";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void updateReiziger(Reiziger reiziger) {
        try {
            Statement myStmt = connect().createStatement();

            String sql = "UPDATE reiziger SET " +
                    "voorletters = '" + reiziger.getVoorletters() + "', ";

            if (reiziger.getTussenvoegsel() != null) {
                sql += "tussenvoegsel = '" + reiziger.getTussenvoegsel() + "', ";
            } else {
                sql += "tussenvoegsel = " + null + ", ";
            }
            sql += "achternaam = '" + reiziger.getAchternaam() + "', " +
                    "geboortedatum = '" + reiziger.getGeboortedatum() + "'" +
                    "WHERE reiziger_id = " + reiziger.getId();

            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void deleteReiziger(Reiziger reiziger) {
        try {
            Statement myStmt = connect().createStatement();
            String sql = "DELETE FROM reiziger WHERE reiziger_id = " + reiziger.getId();
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
