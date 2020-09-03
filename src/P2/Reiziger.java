package P2;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private java.sql.Date geboortedatum;
    private static ArrayList<Reiziger> reizigerList = new ArrayList<>();

    public Reiziger(int id, String vrl, String tus, String atn, String datum) {
        this.id = id;
        voorletters = vrl;
        if (tus.trim() != "") {
            tussenvoegsel = tus;
        }
        achternaam = atn;
        geboortedatum = java.sql.Date.valueOf(datum);
        reizigerList.add(this);
    }

    public int getId() {
        return id;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        if (tussenvoegsel.trim() != "") {
            this.tussenvoegsel = tussenvoegsel;
        } else {
            this.tussenvoegsel = null;
        }
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public Date getGeboortedatum() {
        return geboortedatum;
    }

    public String getNaam() {
        return MessageFormat.format("{0} {1} {2}", voorletters, tussenvoegsel, achternaam);
    }

    public static String getReizigerById(int id){
        for(Reiziger reiziger : reizigerList) {
            if (reiziger.id == id) {
                if (reiziger.tussenvoegsel != null) {
                    return MessageFormat.format("{0} {1} {2}", reiziger.voorletters, reiziger.tussenvoegsel, reiziger.achternaam);
                } else {
                    return MessageFormat.format("{0} {1}", reiziger.voorletters, reiziger.achternaam);
                }
            }
        } return "REIZIGER NOT FOUND";
    }

    @Override
    public String toString() {
        if (this.tussenvoegsel != null) {
            return MessageFormat.format("{0}.\t {1} {2} {3}\t: {4} \n\t Woont op {5};", id, voorletters, tussenvoegsel, achternaam, geboortedatum, Adres.getAdresByReiziger_id(id));
        } else {
            return MessageFormat.format("{0}.\t {1} {2}\t: {3} \n\t Woont op {4};", id, voorletters, achternaam, geboortedatum, Adres.getAdresByReiziger_id(id));
        }
    }
}
