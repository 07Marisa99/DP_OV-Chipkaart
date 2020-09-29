package P2;

import java.sql.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface OVChipkaartDAO {
    List<OVChipkaart> readAllOVKaart();
    List<OVChipkaart> readByReiziger(Reiziger reiziger);
    boolean createOVChipkaart(OVChipkaart ovChipkaart);
    boolean updateOVChipkaart(OVChipkaart ovChipkaart);
    boolean deleteOVChipkaart(OVChipkaart ovChipkaart);
    boolean deleteAllOVChipkaart(Reiziger reiziger);
}