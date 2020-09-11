package P2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaartDAO {
    private Connection connection;

    public OVChipkaartDAO(Connection myConn) {
        connection = myConn;
    }
    private String stringify(ResultSet myRs) throws SQLException {
        String kaart_nummer = myRs.getString("kaart_nummer");
        String geldig_tot = myRs.getString("geldig_tot");
        String klasse = myRs.getString("klasse");
        String saldo = myRs.getString("saldo");
        String reiziger_id = myRs.getString("reiziger_id");
        return MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", kaart_nummer, saldo, klasse, geldig_tot, reiziger_id);
    }

    public List<String> readAllOVKaart() {
        List<String> ovKaarts = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM ov_chipkaart;");
            while (myRs.next()) {
                ovKaarts.add(stringify(myRs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return ovKaarts;
    }

    public OVChipkaart readByReiziger(int id) {
        OVChipkaart ovChipkaart = null;
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres WHERE reiziger_id = " + id);
            while (myRs.next()) {
                ovChipkaart = new OVChipkaart(myRs.getInt("kaart_nummer"), myRs.getString("geldig_tot"), myRs.getInt("klasse"), myRs.getDouble("saldo"), myRs.getInt("reiziger_id"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ovChipkaart;
    }

    public boolean createOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "VALUES (" + ovChipkaart.getId() + ", '" + ovChipkaart.getEnddate() + "', '" + ovChipkaart.getKlasse() + "', '" + ovChipkaart.getSaldo() + "', '" + ovChipkaart.getReiziger_id() + "');";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateOVChipkaart(int id, Date enddate, int klasse, double saldo) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "UPDATE ov_chipkaart SET " +
                    "geldig_tot = '" + enddate +
                    "', klasse = '" + klasse +
                    "', saldo = '" + saldo +
                    "' WHERE kaart_nummer = " + id + ";";

            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteOVChipkaart(int id) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart WHERE kaart_nummer = " + id;
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAllOVChipkaart(int id) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart WHERE reiziger_id = " + id;
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
