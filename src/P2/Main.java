package P2;

import P1.MainFuncties;

//  ik houd nu lijstjes bij in een externe klasse zodat ik op geen moment een object aanmaak wat al bestaat.
//  misschien niet helemaal de juiste manier maar ik kon niks beters bedenken

public class Main {

    public static void main(String[] args) throws Exception {
        MainFuncties mainFuncties = new MainFuncties();
        Connect connect = new Connect();

        ReizigerDAOPostgres reizigerDAOPostgres = new ReizigerDAOPostgres(connect.getConnection(), mainFuncties);
        ProductDAOPostgres productDAOPostgres = new ProductDAOPostgres(connect.getConnection(), mainFuncties);
        AdresDAOPostgres adresDAOPostgres = new AdresDAOPostgres(connect.getConnection(), mainFuncties, reizigerDAOPostgres);
        OVChipkaartDAOPostgres ovChipkaartDAOPostgres = new OVChipkaartDAOPostgres(connect.getConnection(), productDAOPostgres, reizigerDAOPostgres, mainFuncties);
        productDAOPostgres.setOvChipkaartDAOPostgres(ovChipkaartDAOPostgres);

        Product product0 = new Product(14, "fietsvrij", "een dag zonder fiets!", 12.35, "actief", "2020-08-01");
        mainFuncties.addProduct(product0);
        productDAOPostgres.createProduct(product0);
        product0.setBeschrijving("een dagje gratis op de fiets");
        productDAOPostgres.updateProduct(product0);

        reizigerDAOPostgres.readAllReiziger();
        adresDAOPostgres.readAllAdres();
        ovChipkaartDAOPostgres.readAllOVKaart();
        productDAOPostgres.readAllProducts();

        product0.addOVChipkaart(mainFuncties.getReizigers().get(1).getOvChipkaarts().get(2));
        product0.addOVChipkaart(mainFuncties.getReizigers().get(1).getOvChipkaarts().get(1));

        productDAOPostgres.readAllProducts();

        System.out.println(mainFuncties.getReizigers());
        System.out.println();
        System.out.println();
        System.out.println(mainFuncties.getAdresList());
        System.out.println();
        System.out.println();
        System.out.println(mainFuncties.getOvChipkaarts());
        System.out.println();
        System.out.println();
        System.out.println(mainFuncties.getProducts());

        System.out.println(mainFuncties.getOvChipkaarts());

        productDAOPostgres.deleteProduct(product0);

        System.out.println(mainFuncties.getProducts());
    }
}