package P2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface AdresDAO {
    List<Adres> readAllAdres(List<Reiziger> reizigers);
    Adres readByReiziger(Reiziger reiziger);
    boolean createAdress(Adres adres);
    boolean updateAdress(int aID, String pc, String hn, String str, String wp);
    boolean deleteAdress(int id);
}
