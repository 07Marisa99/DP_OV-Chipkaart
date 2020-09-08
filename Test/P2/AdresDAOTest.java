package P2;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdresDAOTest {
    Connect connect = new Connect();
    AdresDAO adresDAO = new AdresDAO(connect.getConnection());
    ReizigerDAO reizigerDAO = new ReizigerDAO(connect.getConnection());
    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
    Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger);

    AdresDAOTest() throws SQLException {
    }

    @Test
    void readAllAdres() {
        assertTrue(adresDAO.readAllAdres());
    }

    @Test
    void readByReiziger() {
        assertTrue(adresDAO.readByReiziger(2));
    }

    @Test
    void createAdress() {
        reizigerDAO.createReiziger(reiziger);
        assertTrue(adresDAO.createAdress(adres));
    }

    @Test
    void updateAdress() {
        assertTrue(adresDAO.updateAdress(6, "1234EF", "78I", "Tiendeweg", "Elfdorp"));
    }

    @Test
    void deleteAdress() {
        assertTrue(adresDAO.deleteAdress(6));
    }

    @Test
    void deleteReiziger() {
        assertTrue(reizigerDAO.deleteReiziger(6, adresDAO));
    }
}