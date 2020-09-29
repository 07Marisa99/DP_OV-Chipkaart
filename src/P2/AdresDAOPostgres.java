package P2;

import P1.MainFuncties;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdresDAOPostgres implements AdresDAO {
    private Connection connection;
    private MainFuncties mainFuncties;
    private ReizigerDAOPostgres reizigerDAOPostgres;

    public AdresDAOPostgres(Connection myConn, MainFuncties mainFuncties, ReizigerDAOPostgres reizigerDAOPostgres) {
        connection = myConn;
        this.mainFuncties = mainFuncties;
        this.reizigerDAOPostgres = reizigerDAOPostgres;
    }
    private Adres exists(int id) {
        for (Adres adres : mainFuncties.getAdresList()) {
            if (adres.getAdresID() == id) {
                return adres;
            }
        } return null;
    }
    private Adres toAdres(ResultSet myRs, Reiziger reiziger) throws SQLException {
        int id = myRs.getInt("adres_id");
        String postcode = myRs.getString("postcode");
        String huisnummer = myRs.getString("huisnummer");
        String straat = myRs.getString("straat");
        String woonplaats = myRs.getString("woonplaats");
        int reizigerId = myRs.getInt("reiziger_id");

        Adres adres = null;
        if (reiziger.getId() == reizigerId) {
            if (exists(id) == null) {
                adres = new Adres(myRs.getInt("adres_id"), myRs.getString("postcode"), myRs.getString("huisnummer"), myRs.getString("straat"), myRs.getString("woonplaats"), reiziger);
                mainFuncties.addAdres(adres);
            }
        } else {
            adres = exists(id);
            adres.setPostcode(postcode);
            adres.setHuisnummer(huisnummer);
            adres.setStraat(straat);
            adres.setWoonplaats(woonplaats);
            return adres;
        } return adres;
//        return MessageFormat.format("{0}.\t {1} {2},\t {3} {4};", id, straat, huisnummer, postcode, woonplaats);
    }

    public List<Adres> readAllAdres() {
        List<Reiziger> reizigers = reizigerDAOPostgres.readAllReiziger();
        List<Adres> adressen = new ArrayList<>();
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres;");
            while (myRs.next()) {
                for (Reiziger reiziger : reizigers) {
                    Adres adres = toAdres(myRs, reiziger);
                    if (adres != null) {
                        adressen.add(adres);
                        reiziger.setAdres(adres);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mainFuncties.getAdresList();
    }

    public Adres readByReiziger(Reiziger reiziger) {
        Adres adres = null;
        try {
            Statement myStmt = connection.createStatement();
            ResultSet myRs = myStmt.executeQuery("SELECT * FROM adres WHERE reiziger_id = " + reiziger.getId());
            while (myRs.next()) {
                adres = toAdres(myRs, reiziger);
                if (adres != null) {
                    reiziger.setAdres(adres);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adres;
    }

    public boolean createAdress(Adres adres) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "INSERT INTO adres (adres_id, postcode, huisnummer, straat, woonplaats, reiziger_id) " +
                    "VALUES (" + adres.getAdresID() + ", '" + adres.getPostcode() + "', '" + adres.getHuisnummer() + "', '" + adres.getStraat() + "', '" + adres.getWoonplaats() + "', " + adres.getReiziger().getId() + ");";
            myStmt.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean updateAdress(Adres adres) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "UPDATE adres SET " +
                    "postcode = '" + adres.getPostcode() +
                    "', huisnummer = '" + adres.getHuisnummer() +
                    "', straat = '" + adres.getStraat() +
                    "', woonplaats = '" + adres.getWoonplaats() +
                    "' WHERE adres_id = " + adres.getAdresID() + ";";

            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean deleteAdress(Adres adres) {
        try {
            Statement myStmt = connection.createStatement();
            String sql = "DELETE FROM adres WHERE reiziger_id = " + adres.getAdresID();
            myStmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
