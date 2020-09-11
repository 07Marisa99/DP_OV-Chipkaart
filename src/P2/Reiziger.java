package P2;

import java.sql.Date;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {
    private int id;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private java.sql.Date geboortedatum;
    private Adres adres;
    private static List<Reiziger> reizigers = new ArrayList<>();
    private List<OVChipkaart> ovChipkaarts = new ArrayList<>();

    public Reiziger(int id, String vrl, String tus, String atn, String datum) {
        this.id = id;
        voorletters = vrl;
        if (tus.trim().equals("")) {
            tussenvoegsel = tus;
        }
        achternaam = atn;
        geboortedatum = java.sql.Date.valueOf(datum);
        reizigers.add(this);
    }

    public void setAdres(Adres adres) {
            this.adres = adres;
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
        if (tussenvoegsel.trim().equals("")) {
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

    public List<OVChipkaart> getOvChipkaarts() {
        return ovChipkaarts;
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarts.add(ovChipkaart);
    }
    public void deleteOvChipkaart(OVChipkaart ovChipkaart) {
        ovChipkaarts.remove(ovChipkaart);
    }

    public static Reiziger getReizigerById(int id) {
        for (Reiziger reiziger : reizigers) {
            if (reiziger.id == id) {
                return reiziger;
            }
        } return null;
    }

    public String toString(boolean check) {
        if (tussenvoegsel != null) {
            return MessageFormat.format("{0} {1} {2}", voorletters, tussenvoegsel, achternaam);
        } else {
            return MessageFormat.format("{0} {1}", voorletters, achternaam);
        }
    }

    @Override
    public String toString() {
        String adresString = "";
        if (adres != null) {
            adresString = adres.toString(true);
        } else {
            adresString = "404, ADRESS NOT FOUND";
        }
        if (this.tussenvoegsel != null) {
            return MessageFormat.format("{0}.\t {1} {2} {3}\t: {4} \n\t Woont op {5};", id, voorletters, tussenvoegsel, achternaam, geboortedatum, adresString);
        } else {
            return MessageFormat.format("{0}.\t {1} {2}\t: {3} \n\t Woont op {4};", id, voorletters, achternaam, geboortedatum, adresString);
        }
    }
}
