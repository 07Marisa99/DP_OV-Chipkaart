package P2;

import P1.MainFuncties;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPostgres implements ReizigerDAO {
    private Connection connection;
    private AdresDAOPostgres adresDAOPostgres;
    private OVChipkaartDAOPostgres ovChipkaartDAOPostgres;
    private ProductDAOPostgres productDAOPostgres;
    private MainFuncties mainFuncties;

    public ReizigerDAOPostgres(Connection myConn, MainFuncties mainFuncties) {
        connection = myConn;
        adresDAOPostgres = new AdresDAOPostgres(connection, mainFuncties, this);
        productDAOPostgres = new ProductDAOPostgres(connection, mainFuncties);
        ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connection, productDAOPostgres, this, mainFuncties);
        this.mainFuncties = mainFuncties;
    }

    private Reiziger exists(int reiziger_id) {
        for (Reiziger reiziger : mainFuncties.getReizigers()) {
            if (reiziger_id == reiziger.getId()) {
                return reiziger;
            }
        } return null;
    }

    private Reiziger toReiziger(ResultSet myRs) throws SQLException {
        int id = myRs.getInt("reiziger_id");
        String vrlt = myRs.getString("voorletters");
        String tus = myRs.getString("tussenvoegsel");
        String anm = myRs.getString("achternaam");
        String dat = myRs.getString("geboortedatum");

        if (tus == null) {
            tus = "";
        }
        Reiziger reiziger;
        if (exists(id) == null) {
            reiziger = new Reiziger(id, vrlt, tus, anm, dat);
            adresDAOPostgres.readByReiziger(reiziger);
            ovChipkaartDAOPostgres.readByReiziger(reiziger);
            mainFuncties.addReiziger(reiziger);
        } else {
            reiziger = exists(id);
            reiziger.setVoorletters(vrlt);
            reiziger.setTussenvoegsel(tus);
            reiziger.setAchternaam(anm);
        }
//        if (tus != null) {
//            System.out.println(MessageFormat.format("{0}.\t {1} {2} {3}\t: {4};", id, vrlt, tus, anm, dat));
//        } else {
//            System.out.println(MessageFormat.format("{0}.\t {1} {2}\t: {3};", id, vrlt, anm, dat));
//        }
        return reiziger;
    }

    public Reiziger readReizigerByID(int id) {
        Reiziger reiziger = null;
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE reiziger_id = " + id);
            while (myRs.next()) {
                reiziger = toReiziger(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return reiziger;
        } return reiziger;
    }

    public List<Reiziger> readReizigerByGebDatum(Date gebDatum) {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE geboortedatum = '" + gebDatum +"';");
            while (myRs.next()) {
                reizigers.add(toReiziger(myRs));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return reizigers;
        } return reizigers;
    }
    public List<Reiziger> readAllReiziger() {
        List<Reiziger> reizigers = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger");

            while (myRs.next()) {
                reizigers.add(toReiziger(myRs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainFuncties.getReizigers();
    }
    public boolean createReiziger(Reiziger reiziger) {
        try {
            Statement myStmt = connection.createStatement();

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
            return false;
        } return true;
    }
    public boolean updateReiziger(Reiziger reiziger) {
        try {

            Statement myStmt = connection.createStatement();

            String sql = "UPDATE reiziger SET " +
                    "voorletters = '" + reiziger.getVoorletters() + "', ";

            if (reiziger.getTussenvoegsel()!= null) {
                sql += "tussenvoegsel = '" + reiziger.getTussenvoegsel() + "', ";
            } else {
                sql += "tussenvoegsel = " + null + ", ";
            }
            sql += "achternaam = '" + reiziger.getAchternaam() + "', " +
                    "geboortedatum = '" + reiziger.getGeboortedatum() + "'" +
                    "WHERE reiziger_id = " + reiziger.getId() +";";

            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
    public boolean deleteReiziger(Reiziger reiziger) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM reiziger WHERE reiziger_id = " + reiziger.getId();
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
}
