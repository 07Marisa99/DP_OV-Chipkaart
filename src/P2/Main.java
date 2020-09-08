package P2;

public class Main {
    public static void main(String[] args) throws Exception {
        Connect connect = new Connect();

        ReizigerDAO reizigerDAO = new ReizigerDAO(connect.getConnection());
        AdresDAO adresDAO = new AdresDAO(connect.getConnection());

        reizigerDAO.readAllReiziger();
        adresDAO.readAllAdres();
        System.out.println();

        Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
        Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger.getId());

        reizigerDAO.createReiziger(reiziger);
        adresDAO.createAdress(adres);

        System.out.println();

        adresDAO.readAllAdres();

        System.out.println();

        adresDAO.readByReiziger(1);
        adresDAO.readByReiziger(6);

        adresDAO.updateAdress(6, "1234EF", "78I", "Tiendeweg", "Elfdorp");

        System.out.println();

        adresDAO.readByReiziger(6);

        System.out.println();

        System.out.println(reiziger.toString());
        System.out.println(adres.toString());

        System.out.println();

        reizigerDAO.readAllReiziger();

        System.out.println();

        reiziger.setTussenvoegsel("de");
        reizigerDAO.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum());
        reizigerDAO.readAllReiziger();

        System.out.println();

        reizigerDAO.readReizigerByID(reiziger.getId());
        reizigerDAO.readReizigerByID(2);

        System.out.println();

        reizigerDAO.readReizigerByGebDatum(reiziger.getGeboortedatum());
        reizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03"));

        System.out.println();

        System.out.println();

        reizigerDAO.deleteReiziger(reiziger.getId(), adresDAO);
        reizigerDAO.readAllReiziger();
    }
}