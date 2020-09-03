package P2;

public class Main {
    public static void main(String[] args) {
        ReizigerDAO.readAllReiziger();
        System.out.println();

        Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
        ReizigerDAO.createReiziger(reiziger);
        ReizigerDAO.readAllReiziger();

        System.out.println();

        reiziger.setTussenvoegsel("de");
        ReizigerDAO.updateReiziger(reiziger);
        ReizigerDAO.readAllReiziger();

        System.out.println();

        ReizigerDAO.readReizigerByID(reiziger.getId());
        ReizigerDAO.readReizigerByID(2);

        System.out.println();

        ReizigerDAO.readReizigerByGebDatum(reiziger.getGeboortedatum());
        ReizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03"));

        System.out.println();

        System.out.println();

        ReizigerDAO.deleteReiziger(reiziger);
        ReizigerDAO.readAllReiziger();
    }
}