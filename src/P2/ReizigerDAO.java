package P2;

import java.sql.*;
import java.text.MessageFormat;

public class ReizigerDAO {
    private Connection connection;

    public ReizigerDAO (Connection myConn) {
        connection = myConn;
    }

    private void stringify(ResultSet myRs) throws SQLException {
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

    public boolean readReizigerByID(int id) {
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE reiziger_id = " + id);
            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }

    public boolean readReizigerByGebDatum(Date gebDatum) {
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger WHERE geboortedatum = '" + gebDatum +"';");
            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
    public boolean readAllReiziger() {
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM reiziger");

            while (myRs.next()) {
                stringify(myRs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
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
    public boolean deleteReiziger(int id, AdresDAO adresDAO) {
        try {
            adresDAO.deleteAdress(id);

            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM reiziger WHERE reiziger_id = " + id;
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } return true;
    }
}
