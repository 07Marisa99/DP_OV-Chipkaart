package P2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaartDAOPostgres implements OVChipkaartDAO {
    private Connection connection;

    public OVChipkaartDAOPostgres(Connection myConn) {
        connection = myConn;
    }
    private OVChipkaart toOVChip(ResultSet myRs, Reiziger reiziger) throws SQLException {
        int kaart_nummer = myRs.getInt("kaart_nummer");
        String geldig_tot = myRs.getString("geldig_tot");
        int klasse = myRs.getInt("klasse");
        double saldo = myRs.getDouble("saldo");
        int reiziger_id = myRs.getInt("reiziger_id");
        OVChipkaart ovChipkaart = null;
        if (reiziger.getId() == reiziger_id) {
            ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger);
        }
//        return MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", kaart_nummer, saldo, klasse, geldig_tot, reiziger_id);
        return ovChipkaart;
    }

    public List<OVChipkaart> readAllOVKaart(List<Reiziger> reizigers) {
        List<OVChipkaart> ovChipkaarts = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM ov_chipkaart;");
            while (myRs.next()) {
                for (Reiziger reiziger : reizigers) {
                    OVChipkaart ovChipkaart = toOVChip(myRs, reiziger);
                    if (ovChipkaart != null) {
                        ovChipkaarts.add(ovChipkaart);
                        reiziger.addOvChipkaart(ovChipkaart);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } return ovChipkaarts;
    }

    public List<OVChipkaart> readByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarts = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres WHERE reiziger_id = " + reiziger.getId());
            while (myRs.next()) {
                ovChipkaarts.add(toOVChip(myRs, reiziger));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ovChipkaarts;
    }

    public boolean createOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "INSERT INTO ov_chipkaart (kaart_nummer, geldig_tot, klasse, saldo, reiziger_id) " +
                    "VALUES (" + ovChipkaart.getId() + ", '" + ovChipkaart.getEnddate() + "', '" + ovChipkaart.getKlasse() + "', '" + ovChipkaart.getSaldo() + "', '" + ovChipkaart.getReiziger().getId() + "');";
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
