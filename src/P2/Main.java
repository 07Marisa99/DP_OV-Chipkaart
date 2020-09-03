package P2;

public class Main {
    public static void main(String[] args) {
        ReizigerDAO.readAllReiziger();
        AdresDAO.readAllAdres();
        System.out.println();

        Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
        Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", 6);

        ReizigerDAO.createReiziger(reiziger);
        AdresDAO.createAdress(adres);

        System.out.println();

        AdresDAO.readAllAdres();

        System.out.println();

        AdresDAO.readByReiziger(1);
        AdresDAO.readByReiziger(6);

        AdresDAO.updateAdress(6, "1234EF", "78I", "Tiendeweg", "Elfdorp");

        System.out.println();

        AdresDAO.readByReiziger(6);

        System.out.println();

        System.out.println(reiziger.toString());
        System.out.println(adres.toString());

        System.out.println();

        ReizigerDAO.readAllReiziger();

        System.out.println();

        reiziger.setTussenvoegsel("de");
        ReizigerDAO.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum());
        ReizigerDAO.readAllReiziger();

        System.out.println();

        ReizigerDAO.readReizigerByID(reiziger.getId());
        ReizigerDAO.readReizigerByID(2);

        System.out.println();

        ReizigerDAO.readReizigerByGebDatum(reiziger.getGeboortedatum());
        ReizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03"));

        System.out.println();

        System.out.println();

        ReizigerDAO.deleteReiziger(reiziger.getId());
        ReizigerDAO.readAllReiziger();
    }
}