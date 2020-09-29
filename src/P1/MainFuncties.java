package P1;

import P2.*;

import java.util.ArrayList;
import java.util.List;

public class MainFuncties {
    private List<Reiziger> reizigers = new ArrayList<>();
    private List<Adres> adresList = new ArrayList<>();
    private List<OVChipkaart> ovChipkaarts = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
    public MainFuncties() {

    }
    public List<Reiziger> getReizigers() {
        return reizigers;
    }

    public void addReiziger(Reiziger reiziger) {
        this.reizigers.add(reiziger);
    }

    public List<Adres> getAdresList() {
        return adresList;
    }

    public void addAdres(Adres adres) {
        this.adresList.add(adres);
    }

    public List<OVChipkaart> getOvChipkaarts() {
        return ovChipkaarts;
    }

    public void addOvChipkaart(OVChipkaart ovChipkaart) {
        this.ovChipkaarts.add(ovChipkaart);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    private void deleteReiziger(ReizigerDAOPostgres reizigerDAOPostgres, AdresDAOPostgres adresDAOPostgres, OVChipkaartDAOPostgres ovChipkaartDAOPostgres, Reiziger reiziger) {
        adresDAOPostgres.deleteAdress(reiziger.getAdres());
        ovChipkaartDAOPostgres.deleteAllOVChipkaart(reiziger);
        reizigerDAOPostgres.deleteReiziger(reiziger);
    }
}
