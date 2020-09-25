package P2;

import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        Connect connect = new Connect();

        ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection());
        ProductDAOPostgres productDAOPostgres = new ProductDAOPostgres(connect.getConnection());
        List<Reiziger> reizigers = reizigerDAOPostgres.readAllReiziger();
        System.out.println(reizigers);
        System.out.println();

        System.out.println(reizigerDAOPostgres.readReizigerByID(2));
        System.out.println();

        Product product = new Product(14, "fietsvrij", "een dag zonder fiets!", 12.35, "actief", "2020-08-01", reizigers.get(1).getOvChipkaarts().get(2));
        productDAOPostgres.createProduct(product);

        System.out.println(reizigerDAOPostgres.readReizigerByID(2));
    }
}