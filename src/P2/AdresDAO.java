package P2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface AdresDAO {
    List<Adres> readAllAdres();
    Adres readByReiziger(Reiziger reiziger);
    boolean createAdress(Adres adres);
    boolean updateAdress(Adres adres);
    boolean deleteAdress(Adres adres);
}
