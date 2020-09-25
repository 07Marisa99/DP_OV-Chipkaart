//package P2;
//
//import org.junit.jupiter.api.Test;
//
//import java.sql.SQLException;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ReizigerDAOPostgresTest {
//    Reiziger reiziger = new Reiziger(6, "S", "", "Waal", "1974-10-8");
//    Connect connect = new Connect();
//    ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection());
//    AdresDAOPostgres adresDAOPostgres = new AdresDAOPostgres(connect.getConnection());
//    OVChipkaartDAOPostgres ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connect.getConnection());
//
//    ReizigerDAOPostgresTest() throws SQLException {
//    }
//
//    @Test
//    void readReizigerByID() {
////        assertTrue(reizigerDAOPostgres.readReizigerByID(3));
//    }
//
//    @Test
//    void readReizigerByGebDatum() {
////        assertTrue(reizigerDAOPostgres.readReizigerByGebDatum(java.sql.Date.valueOf("2002-12-03")));
//    }
//
//    @Test
//    void readAllReiziger() {
////        assertTrue(reizigerDAOPostgres.readAllReiziger());
//    }
//
//    @Test
//    void createReiziger() {
//        assertTrue(reizigerDAOPostgres.createReiziger(reiziger));
//    }
//
//    @Test
//    void updateReiziger() {
//        reiziger.setAchternaam("Samson");
//        assertTrue(reizigerDAOPostgres.updateReiziger(reiziger.getId(), reiziger.getVoorletters(), reiziger.getTussenvoegsel(), reiziger.getAchternaam(), reiziger.getGeboortedatum()));
//    }
//
//    @Test
//    void deleteReiziger() {
//        assertTrue(reizigerDAOPostgres.deleteReiziger(reiziger.getId(), adresDAOPostgres, ovChipkaartDAOPostgres));
//    }
//}