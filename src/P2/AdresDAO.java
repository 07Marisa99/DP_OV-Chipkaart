package P2;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class AdresDAO {
    private Connection connection;

    public AdresDAO(Connection myConn) {
        connection = myConn;
    }
    private String stringify(ResultSet myRs) throws SQLException {
        String id = myRs.getString("adres_id");
        String postcode = myRs.getString("postcode");
        String huisnummer = myRs.getString("huisnummer");
        String straat = myRs.getString("straat");
        String woonplaats = myRs.getString("woonplaats");
        return MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", id, straat, huisnummer, postcode, woonplaats);
    }

    public boolean readAllAdres() {
        try {
            List<String> adressen = new ArrayList<>();
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres;");
            while (myRs.next()) {
                adressen.add(stringify(myRs));
            }
            System.out.println(adressen);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean readByReiziger(int id) {
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres WHERE reiziger_id = " + id);
            while (myRs.next()) {
                System.out.println(stringify(myRs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean createAdress(Adres adres) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                    "VALUES (" + adres.getAdresID() + ", '" + adres.getPostcode() + "', '" + adres.getHuisnummer() + "', '" + adres.getStraat() + "', '" + adres.getWoonplaats() + "', " + adres.getReiziger_id() + ");";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateAdress(int aID, String pc, String hn, String str, String wp) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "UPDATE adres SET " +
                    "postcode = '" + pc +
                    "', huisnummer = '" + hn +
                    "', straat = '" + str +
                    "', woonplaats = '" + wp +
                    "' WHERE adres_id = " + aID + ";";

            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAdress(int id) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM adres WHERE reiziger_id = " + id;
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
