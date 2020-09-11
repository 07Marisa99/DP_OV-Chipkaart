package P2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OVChipkaart {
    private int id;
    private Date enddate;
    private int klasse;
    private double saldo;
    private int reiziger_id;
    private Reiziger reiziger;
    private List<Product> products = new ArrayList<>();

    public OVChipkaart(int ovID, String end, int klas, double sal, int rID, Reiziger reiziger) {
        id = ovID;
        enddate = java.sql.Date.valueOf(end);
        klasse = klas;
        saldo = sal;
        reiziger_id = rID;
        this.reiziger = reiziger;
        reiziger.addOvChipkaart(this);
    }
    public OVChipkaart(int ovID, String end, int klas, double sal, int rID) {
        id = ovID;
        enddate = java.sql.Date.valueOf(end);
        klasse = klas;
        saldo = sal;
        reiziger_id = rID;
        reiziger = Reiziger.getReizigerById(rID);
    }

    public int getId() {
        return id;
    }

    public Date getEnddate() {
        return enddate;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getReiziger_id() {
        return reiziger_id;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void addProduct(Product product) {
        products.add(product);
    }
    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
