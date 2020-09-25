package P2;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
    Reiziger readReizigerByID(int id);
    List<Reiziger> readReizigerByGebDatum(Date gebDatum);
    List<Reiziger> readAllReiziger();
    boolean createReiziger(Reiziger reiziger);
    boolean updateReiziger(int getId, String getVoorletters, String getTussenvoegsel, String getAchternaam, Date getGeboortedatum);
    boolean deleteReiziger(int id, AdresDAOPostgres adresDAOPostgres, OVChipkaartDAOPostgres ovChipkaartDAOPostgres);
}
