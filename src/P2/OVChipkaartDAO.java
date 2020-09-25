package P2;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface OVChipkaartDAO {
    List<OVChipkaart> readAllOVKaart(List<Reiziger> reizigers);
    List<OVChipkaart> readByReiziger(Reiziger reiziger);
    boolean createOVChipkaart(OVChipkaart ovChipkaart);
    boolean updateOVChipkaart(int id, Date enddate, int klasse, double saldo);
    boolean deleteOVChipkaart(int id);
    boolean deleteAllOVChipkaart(int id);
}