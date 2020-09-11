package P2;

public class Main {
    public static void main(String[] args) throws Exception {
        Connect connect = new Connect();

        ReizigerDAO reizigerDAO = new ReizigerDAO(connect.getConnection());
        AdresDAO adresDAO = new AdresDAO(connect.getConnection());
        OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAO(connect.getConnection());

        reizigerDAO.readAllReiziger();

        System.out.println();

        for (String a : adresDAO.readAllAdres()) {
            System.out.println(a);
        }

        System.out.println();

        for (String ovKaart : ovChipkaartDAO.readAllOVKaart()) {
            System.out.println(ovKaart);
        }

        System.out.println();

        Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
        Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger);
        OVChipkaart ovChipkaart = new OVChipkaart(15373, "2025-9-8", 2, 0.00, reiziger.getId(), reiziger);
        OVChipkaart ovChipkaart2 = new OVChipkaart(15374, "2025-9-8", 2, 0.00, reiziger.getId(), reiziger);

        reiziger.setAdres(adres);

        reizigerDAO.createReiziger(reiziger);
        adresDAO.createAdress(adres);
        ovChipkaartDAO.createOVChipkaart(ovChipkaart);

        for (String a : adresDAO.readAllAdres()) {
            System.out.println(a);
        }

        System.out.println();

        for (String ov : ovChipkaartDAO.readAllOVKaart()) {
            System.out.println(ov);
        }

        System.out.println();

        reizigerDAO.readAllReiziger();

        System.out.println();

        System.out.println(adresDAO.readByReiziger(1));
        System.out.println(adresDAO.readByReiziger(6));

        System.out.println();

        reiziger.setTussenvoegsel("de");
        reizigerDAO.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum());

        adres.setWoonplaats("Elfdorp");
        adresDAO.updateAdress(adres.getAdresID(), adres.getPostcode(), adres.getHuisnummer(), adres.getStraat(), adres.getWoonplaats());

        ovChipkaart.setKlasse(1);
        ovChipkaartDAO.updateOVChipkaart(ovChipkaart.getId(), ovChipkaart.getEnddate(), ovChipkaart.getKlasse(), ovChipkaart.getSaldo());

        adresDAO.readByReiziger(6);

        System.out.println();

        System.out.println(reiziger.toString());
        System.out.println(adres.toString());

        System.out.println();

        reizigerDAO.readAllReiziger();

        System.out.println();

        reizigerDAO.readAllReiziger();

        System.out.println();

        reizigerDAO.readReizigerByID(reiziger.getId());
        reizigerDAO.readReizigerByID(2);

        System.out.println();

        reizigerDAO.readReizigerByGebDatum(reiziger.getGeboortedatum());
        reizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03"));

        System.out.println();

        ovChipkaartDAO.deleteOVChipkaart(ovChipkaart.getId());

        for (String ov : ovChipkaartDAO.readAllOVKaart()) {
            System.out.println(ov);
        }

        System.out.println();

        ovChipkaartDAO.createOVChipkaart(ovChipkaart);
        ovChipkaartDAO.createOVChipkaart(ovChipkaart2);

        for (String ov : ovChipkaartDAO.readAllOVKaart()) {
            System.out.println(ov);
        }

        System.out.println();

        reizigerDAO.deleteReiziger(reiziger.getId(), adresDAO, ovChipkaartDAO);
        reizigerDAO.readAllReiziger();
        System.out.println();
        
        for (String ov : ovChipkaartDAO.readAllOVKaart()) {
            System.out.println(ov);
        }
    }
}