package P2;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdresDAOTest {
    Connect connect = new Connect();
    AdresDAO adresDAO = new AdresDAO(connect.getConnection());
    ReizigerDAO reizigerDAO = new ReizigerDAO(connect.getConnection());
    OVChipkaartDAO ovChipkaartDAO = new OVChipkaartDAO(connect.getConnection());
    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
    Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger);

    AdresDAOTest() throws SQLException {
    }

    @Test
    void readAllAdres() {
        List list =  new ArrayList<>();
        list.add("1.\t Visschersplein  37,\t 3511LX Utrecht;");
        list.add("2.\t Jaarbeursplein 6A,\t 3521AL Utrecht;");
        list.add("3.\t Stadsbrink 375,\t 6707AA Wageningen;");
        list.add("4.\t Arnhemseweg 4,\t 3817CH Amersfoort;");
        list.add("5.\t Vermeulenstraat  22,\t 3572WP Utrecht;");
        assertEquals(list, adresDAO.readAllAdres());
    }

    @Test
    void readByReiziger() {
        assertEquals("2.\t Jaarbeursplein 6A, 3521AL Utrecht\n" +
                "\t Wordt bewoond door: NO REIZIGER FOUND;" ,adresDAO.readByReiziger(2).toString());
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
        assertTrue(reizigerDAO.deleteReiziger(6, adresDAO, ovChipkaartDAO));
    }
}