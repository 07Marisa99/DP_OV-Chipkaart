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
    private Reiziger reiziger;
    private static ArrayList<Adres> adresList = new ArrayList<>();

    public Adres(int aID, String pc, String hn, String str, String wp, Reiziger reiziger) {
        adresID = aID;
        postcode = pc;
        huisnummer = hn;
        straat = str;
        woonplaats = wp;
        reiziger_id = reiziger.getId();
        this.reiziger = reiziger;
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


    public String toString(boolean check) {
        return MessageFormat.format("Adres: {0} {1}, {2} {3}", straat, huisnummer, postcode, woonplaats);
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}.\t {1} {2}, {3} {4}\n\t Wordt bewoond door: {5};", adresID, straat, huisnummer, postcode, woonplaats, reiziger.toString(true));
    }
}