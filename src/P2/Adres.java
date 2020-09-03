package P2;

import java.text.MessageFormat;
import java.util.ArrayList;

public class Adres {
    private int adresID;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private int reiziger_id;
    private static ArrayList<Adres> adresList = new ArrayList<>();

    public Adres(int aID, String pc, String hn, String str, String wp, int rID) {
        adresID = aID;
        postcode = pc;
        huisnummer = hn;
        straat = str;
        woonplaats = wp;
        reiziger_id = rID;
        adresList.add(this);
    }

    public int getAdresID() {
        return adresID;
    }

    public String getStraat() {
        return straat;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public static String getAdresByReiziger_id(int id) {
        for (Adres adres : adresList) {
            if (adres.getReiziger_id() == id) {
                return MessageFormat.format("Adres: {0} {1}, {2} {3}", adres.straat, adres.huisnummer, adres.postcode, adres.woonplaats);
            }
        } return "ADDRESS NOT FOUND";
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}.\t {1} {2}, {3} {4}\n\t Wordt bewoond door: {5};", adresID, straat, huisnummer, postcode, woonplaats, Reiziger.getReizigerById(reiziger_id));
    }
}