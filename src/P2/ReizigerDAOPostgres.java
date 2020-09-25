package P2;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ReizigerDAOPostgres implements ReizigerDAO {
    private Connection connection;
    AdresDAOPostgres adresDAOPostgres = null;
    OVChipkaartDAOPostgres ovChipkaartDAOPostgres = null;

    public ReizigerDAOPostgres(Connection myConn) {
        connection = myConn;
        adresDAOPostgres = new AdresDAOPostgres(connection);
        ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connection);
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

        Reiziger reiziger = new Reiziger(id, vrlt, tus, anm, dat);
        adresDAOPostgres.readByReiziger(reiziger);
        ovChipkaartDAOPostgres.readByReiziger(reiziger);

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
            return reizigers;
        } return reizigers;
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
    public boolean updateReiziger(int getId, String getVoorletters, String getTussenvoegsel, String getAchternaam, Date getGeboortedatum) {
        try {
            Statement myStmt = connection.createStatement();

            String sql = "UPDATE reiziger SET " +
                    "voorletters = '" + getVoorletters + "', ";

            if (getTussenvoegsel!= null) {
                sql += "tussenvoegsel = '" + getTussenvoegsel + "', ";
            } else {
                sql += "tussenvoegsel = " + null + ", ";
            }
            sql += "achternaam = '" + getAchternaam + "', " +
                    "geboortedatum = '" + getGeboortedatum + "'" +
                    "WHERE reiziger_id = " + getId +";";

            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
    public boolean deleteReiziger(int id, AdresDAOPostgres adresDAOPostgres, OVChipkaartDAOPostgres ovChipkaartDAOPostgres) {
        try {
            adresDAOPostgres.deleteAdress(id);
            ovChipkaartDAOPostgres.deleteAllOVChipkaart(id);

            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM reiziger WHERE reiziger_id = " + id;
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
}
