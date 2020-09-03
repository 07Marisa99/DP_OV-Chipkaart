package P2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReizigerDAOTest {
    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");

    @Test
    void readReizigerByID() {
        assertTrue(ReizigerDAO.readReizigerByID(3));
    }

    @Test
    void readReizigerByGebDatum() {
        assertTrue(ReizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03")));
    }

    @Test
    void readAllReiziger() {
        assertTrue(ReizigerDAO.readAllReiziger());
    }

    @Test
    void createReiziger() {
        assertTrue(ReizigerDAO.createReiziger(reiziger));
    }

    @Test
    void updateReiziger() {
        reiziger.setAchternaam("Samson");
        assertTrue(ReizigerDAO.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum()));
    }

    @Test
    void deleteReiziger() {
        assertTrue(ReizigerDAO.deleteReiziger(reiziger));
    }
}