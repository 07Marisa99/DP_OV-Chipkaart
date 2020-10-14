package P2;

import java.util.List;

public interface AdresDAO {
    List<Adres> readAllAdres();
    Adres readByReiziger(Reiziger reiziger);
    boolean createAdress(Adres adres);
    boolean updateAdress(Adres adres);
    boolean deleteAdress(Adres adres);
}