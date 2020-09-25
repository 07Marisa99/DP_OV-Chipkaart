package P2;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Connect connect = new Connect();

        ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection());
        AdresDAOPostgres adresDAOPostgres = new AdresDAOPostgres(connect.getConnection());
        OVChipkaartDAOPostgres ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connect.getConnection());

        List<Reiziger> reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);

        System.out.println();

        List<Adres> adressen = adresDAOPostgres.readAllAdres(reizigers);
        System.out.println(adressen);

        List<OVChipkaart> ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart(reizigers);
        System.out.println(ovChipkaarts);

        System.out.println();

        Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
        Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger);
        OVChipkaart ovChipkaart = new OVChipkaart(15373, "2025-9-8", 2, 0.00, reiziger);
        OVChipkaart ovChipkaart2 = new OVChipkaart(15374, "2025-9-8", 2, 0.00, reiziger);

        reiziger.setAdres(adres);

        reizigerDAOPostgres.createReiziger(reiziger);
        adresDAOPostgres.createAdress(adres);
        ovChipkaartDAOPostgres.createOVChipkaart(ovChipkaart);

        adressen = adresDAOPostgres.readAllAdres(reizigers);
        System.out.println(adressen);

        System.out.println();

        ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart(reizigers);
        System.out.println(ovChipkaarts);

        System.out.println();

        System.out.println(reizigers);
        reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);

        System.out.println();

        System.out.println(adresDAOPostgres.readByReiziger(reizigers.get(0)));
        System.out.println(adresDAOPostgres.readByReiziger(reizigers.get(5)));

        System.out.println();

        reiziger.setTussenvoegsel("de");
        reizigerDAOPostgres.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum());

        adres.setWoonplaats("Elfdorp");
        adresDAOPostgres.updateAdress(adres.getAdresID(), adres.getPostcode(), adres.getHuisnummer(), adres.getStraat(), adres.getWoonplaats());

        ovChipkaart.setKlasse(1);
        ovChipkaartDAOPostgres.updateOVChipkaart(ovChipkaart.getId(), ovChipkaart.getEnddate(), ovChipkaart.getKlasse(), ovChipkaart.getSaldo());

        System.out.println(adresDAOPostgres.readByReiziger(reizigers.get(5)));

        System.out.println();

        System.out.println(reiziger.toString());
        System.out.println(adres.toString());

        System.out.println();

        reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);

        System.out.println();

        reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);

        System.out.println();

        System.out.println(reizigerDAOPostgres.readReizigerByID(reiziger.getId()));
        System.out.println(reizigerDAOPostgres.readReizigerByID(2));

        System.out.println();

        System.out.println(reizigerDAOPostgres.readReizigerByGebDatum(reiziger.getGeboortedatum()));
        System.out.println(reizigerDAOPostgres.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03")));

        System.out.println();

        ovChipkaartDAOPostgres.deleteOVChipkaart(ovChipkaart.getId());


        ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart(reizigers);
        System.out.println(ovChipkaarts);

        System.out.println();

        ovChipkaartDAOPostgres.createOVChipkaart(ovChipkaart);
        ovChipkaartDAOPostgres.createOVChipkaart(ovChipkaart2);

        ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart(reizigers);
        System.out.println(ovChipkaarts);

        System.out.println();

        reizigerDAOPostgres.deleteReiziger(reiziger.getId(), adresDAOPostgres, ovChipkaartDAOPostgres);
        reizigerDAOPostgres.readAllReiziger();
        System.out.println();

        ovChipkaarts = ovChipkaartDAOPostgres.readAllOVKaart(reizigers);
        System.out.println(ovChipkaarts);
    }
}