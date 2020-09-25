package P2;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Connect connect = new Connect();

        ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection());
        List<Reiziger> reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);
        System.out.println();
        System.out.println(reizigerDAOPostgres.readReizigerByID(2));
    }
}