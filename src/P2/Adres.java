package P2;

import java.text.MessageFormat;

public class Adres {
    private int adresID;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;
    private Reiziger reiziger;

    public Adres(int aID, String pc, String hn, String str, String wp, Reiziger reiziger) {
        adresID = aID;
        postcode = pc;
        huisnummer = hn;
        straat = str;
        woonplaats = wp;
        this.reiziger = reiziger;
    }

    public Reiziger getReiziger() {
        return reiziger;
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

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }

    public String toString(boolean check) {
        return MessageFormat.format("Adres: {0} {1}, {2} {3}", straat, huisnummer, postcode, woonplaats);
    }

    @Override
    public String toString() {
        if (reiziger != null) {
            return MessageFormat.format("\n{0}.\t {1} {2}, {3} {4}\n\t Wordt bewoond door: {5};", adresID, straat, huisnummer, postcode, woonplaats, reiziger.toString(true));
        } else {
            return MessageFormat.format("\n{0}.\t {1} {2}, {3} {4}\n\t Wordt bewoond door: {5};", adresID, straat, huisnummer, postcode, woonplaats, "NO REIZIGER FOUND");
        }
    }
}