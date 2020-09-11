package P2;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ReizigerDAOTest {
    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
    Connect connect = new Connect();
    ReizigerDAO reizigerDAO = new ReizigerDAO(connect.getConnection());
    AdresDAO adresDAO = new AdresDAO(connect.getConnection());
    OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAO(connect.getConnection());

    ReizigerDAOTest() throws SQLException {
    }

    @Test
    void readReizigerByID() {
        assertTrue(reizigerDAO.readReizigerByID(3));
    }

    @Test
    void readReizigerByGebDatum() {
        assertTrue(reizigerDAO.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03")));
    }

    @Test
    void readAllReiziger() {
        assertTrue(reizigerDAO.readAllReiziger());
    }

    @Test
    void createReiziger() {
        assertTrue(reizigerDAO.createReiziger(reiziger));
    }

    @Test
    void updateReiziger() {
        reiziger.setAchternaam("Samson");
        assertTrue(reizigerDAO.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum()));
    }

    @Test
    void deleteReiziger() {
        assertTrue(reizigerDAO.deleteReiziger(reiziger.getId(), adresDAO, ovChipkaartDAO));
    }
}