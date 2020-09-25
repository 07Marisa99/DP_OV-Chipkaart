//package P2;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AdresDAOPostgresTest {
//    Connect connect = new Connect();
//    AdresDAOPostgres adresDAOPostgres = new AdresDAOPostgres(connect.getConnection());
//    ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection());
//    OVChipkaartDAOPostgres ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connect.getConnection());
//    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
//    Adres adres = new Adres(6, "1234EF", "78I", "Tiendeweg", "Elfstad", reiziger);
//
//    AdresDAOPostgresTest() throws SQLException {
//    }
//
//    @Test
//    void readAllAdres() {
//        List list =  new ArrayList<>();
//        list.add("1.\t Visschersplein  37,\t 3511LX Utrecht;");
//        list.add("2.\t Jaarbeursplein 6A,\t 3521AL Utrecht;");
//        list.add("3.\t Stadsbrink 375,\t 6707AA Wageningen;");
//        list.add("4.\t Arnhemseweg 4,\t 3817CH Amersfoort;");
//        list.add("5.\t Vermeulenstraat  22,\t 3572WP Utrecht;");
//        assertEquals(list, adresDAOPostgres.readAllAdres());
//    }
//
//    @Test
//    void readByReiziger() {
//        assertEquals("2.\t Jaarbeursplein 6A, 3521AL Utrecht\n" +
//                "\t Wordt bewoond door: NO REIZIGER FOUND;" , adresDAOPostgres.readByReiziger(2).toString());
//    }
//
//    @Test
//    void createAdress() {
//        reizigerDAOPostgres.createReiziger(reiziger);
//        assertTrue(adresDAOPostgres.createAdress(adres));
//    }
//
//    @Test
//    void updateAdress() {
//        assertTrue(adresDAOPostgres.updateAdress(6, "1234EF", "78I", "Tiendeweg", "Elfdorp"));
//    }
//
//    @Test
//    void deleteAdress() {
//        assertTrue(adresDAOPostgres.deleteAdress(6));
//    }
//
//    @Test
//    void deleteReiziger() {
//        assertTrue(reizigerDAOPostgres.deleteReiziger(6, adresDAOPostgres, ovChipkaartDAOPostgres));
//    }
//}