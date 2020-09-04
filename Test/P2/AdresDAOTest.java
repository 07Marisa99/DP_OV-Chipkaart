package P2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdresDAOTest {
    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
    Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger.getId());

    @Test
    void readAllAdres() {
        assertTrue(AdresDAO.readAllAdres());
    }

    @Test
    void readByReiziger() {
        assertTrue(AdresDAO.readByReiziger(2));
    }

    @Test
    void createAdress() {
        ReizigerDAO.createReiziger(reiziger);
        assertTrue(AdresDAO.createAdress(adres));
    }

    @Test
    void updateAdress() {
        assertTrue(AdresDAO.updateAdress(6, "1234EF", "78I", "Tiendeweg", "Elfdorp"));
    }

    @Test
    void deleteAdress() {
        assertTrue(AdresDAO.deleteAdress(6));
    }

    @Test
    void deleteReiziger() {
        assertTrue(ReizigerDAO.deleteReiziger(6));
    }
}