package P2;

import java.sql.*;
import java.text.MessageFormat;

public class AdresDAO {
    private static void stringify(ResultSet myRs) throws SQLException {
        String id = myRs.getString("adres_id");
        String postcode = myRs.getString("postcode");
        String huisnummer = myRs.getString("huisnummer");
        String straat = myRs.getString("straat");
        String woonplaats = myRs.getString("woonplaats");
        System.out.println(MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", id, straat, huisnummer, postcode, woonplaats));
    }

    public static boolean readByReiziger(int id) {
        try {
            Statement myStmt = Connect.connect().createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres WHERE reiziger_id = " + id);
            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean createAdress(Adres adres) {
        try {
            Statement myStmt = Connect.connect().createStatement();
            String sql = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                    "VALUES (" + adres.getAdresID() + ", '" + adres.getPostcode() + "', '" + adres.getHuisnummer() + "', '" + adres.getStraat() + "', '" + adres.getWoonplaats() + "', " + adres.getReiziger_id() + ");";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean updateAdress() {
        try {
            Statement myStmt = Connect.connect().createStatement();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean deleteAdress(int id) {
        try {
            Statement myStmt = Connect.connect().createStatement();
            String sql = "DELETE FROM adres WHERE reiziger_id = " + id;
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
