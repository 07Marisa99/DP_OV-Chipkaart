package P2;

import P1.MainFuncties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OVChipkaartDAOPostgres implements OVChipkaartDAO {
    private Connection connection;
    private ProductDAOPostgres productDAOPostgres;
    private ReizigerDAOPostgres reizigerDAOPostgres;
    private MainFuncties mainFuncties;

    public OVChipkaartDAOPostgres(Connection myConn, ProductDAOPostgres productDAOPostgres, ReizigerDAOPostgres reizigerDAOPostgres, MainFuncties mainFuncties) {
        connection = myConn;
        this.productDAOPostgres = productDAOPostgres;
        this.reizigerDAOPostgres = reizigerDAOPostgres;
        this.mainFuncties = mainFuncties;
    }

    private OVChipkaart exists(int kaart_nummer) {
        for (OVChipkaart ovChipkaart : mainFuncties.getOvChipkaarts()) {
            if (ovChipkaart.getId() == kaart_nummer) {
                return ovChipkaart;
            }
        } return null;
    }

    private OVChipkaart toOVChip(ResultSet myRs, Reiziger reiziger) throws SQLException {
        int kaart_nummer = myRs.getInt("kaart_nummer");
        String geldig_tot = myRs.getString("geldig_tot");
        int klasse = myRs.getInt("klasse");
        double saldo = myRs.getDouble("saldo");
        int reiziger_id = myRs.getInt("reiziger_id");

        OVChipkaart ovChipkaart = null;
        if (reiziger.getId() == reiziger_id) {
            if (exists(kaart_nummer) == null) {
                ovChipkaart = new OVChipkaart(kaart_nummer, geldig_tot, klasse, saldo, reiziger);
                mainFuncties.addOvChipkaart(ovChipkaart);
                productDAOPostgres.readProductByOV(ovChipkaart);
            }
        } else {
            ovChipkaart = exists(kaart_nummer);
            ovChipkaart.setProducts(productDAOPostgres.readProductByOV(ovChipkaart));
            ovChipkaart.setKlasse(klasse);
            ovChipkaart.setReiziger(reiziger);
            ovChipkaart.setSaldo(saldo);
        }
//        return MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", kaart_nummer, saldo, klasse, geldig_tot, reiziger_id);
        return ovChipkaart;
    }

    public List<OVChipkaart> readAllOVKaart() {
        List<Reiziger> reizigers = reizigerDAOPostgres.readAllReiziger();
        List<OVChipkaart> ovChipkaarts = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM ov_chipkaart;");
            while (myRs.next()) {
                for (Reiziger reiziger : reizigers) {
                    toOVChip(myRs, reiziger);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainFuncties.getOvChipkaarts();
    }

    public List<OVChipkaart> readByReiziger(Reiziger reiziger) {
        List<OVChipkaart> ovChipkaarts = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM ov_chipkaart WHERE reiziger_id = " + reiziger.getId() + ";");
            while (myRs.next()) {
                OVChipkaart ovChipkaart = toOVChip(myRs, reiziger);
                if (ovChipkaart != null) {
                    ovChipkaarts.add(ovChipkaart);
                }
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

    public boolean updateOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "UPDATE ov_chipkaart SET " +
                    "geldig_tot = '" + ovChipkaart.getEnddate() +
                    "', klasse = '" + ovChipkaart.getKlasse() +
                    "', saldo = '" + ovChipkaart.getSaldo() +
                    "' WHERE kaart_nummer = " + ovChipkaart.getId() + ";";

            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteOVChipkaart(OVChipkaart ovChipkaart) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart WHERE kaart_nummer = " + ovChipkaart.getId();
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAllOVChipkaart(Reiziger reiziger) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM ov_chipkaart WHERE reiziger_id = " + reiziger.getId();
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
